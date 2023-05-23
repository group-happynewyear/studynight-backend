package kr.happynewyear.authentication.application.service

import kr.happynewyear.authentication.domain.model.User
import kr.happynewyear.authentication.infrastructure.database.RefreshTokenChainJpaRepository
import kr.happynewyear.authentication.infrastructure.database.RefreshTokenJpaRepository
import kr.happynewyear.authentication.infrastructure.database.UserJpaRepository
import kr.happynewyear.library.test.ApplicationTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

class TokenServiceTest(
    @Autowired val tokenService: TokenService,
    @Autowired val userJpaRepository: UserJpaRepository,
    @Autowired val refreshTokenJpaRepository: RefreshTokenJpaRepository,
    @Autowired val refreshTokenChainJpaRepository: RefreshTokenChainJpaRepository
) : ApplicationTest() {


    @Test
    fun delete_withChain() {
        val user = userJpaRepository.save(User.create("email@email.com"))
        val token = tokenService.issue(user)
        val chain = refreshTokenJpaRepository.findByIdOrNull(token.refreshTokenId)!!.refreshTokenChain

        tokenService.delete(token.refreshTokenId)

        assertThat(refreshTokenJpaRepository.existsById(token.refreshTokenId)).isFalse
        assertThat(refreshTokenChainJpaRepository.existsById(chain.id)).isFalse
    }

    @Test
    fun delete_withoutChain() {
        val user = userJpaRepository.save(User.create("email@email.com"))
        val token = tokenService.issue(user)
        val chain = refreshTokenJpaRepository.findByIdOrNull(token.refreshTokenId)!!.refreshTokenChain
        tokenService.refresh(token.refreshTokenId)

        tokenService.delete(token.refreshTokenId)

        assertThat(refreshTokenJpaRepository.existsById(token.refreshTokenId)).isFalse
        assertThat(refreshTokenChainJpaRepository.existsById(chain.id)).isTrue
    }

}
