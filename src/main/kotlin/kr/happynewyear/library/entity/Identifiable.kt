package kr.happynewyear.library.entity

import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PostLoad
import jakarta.persistence.PostPersist
import kr.happynewyear.library.utility.Randoms
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.domain.Persistable
import java.util.*

@MappedSuperclass
abstract class Identifiable : Persistable<UUID> {

    @Id
    private val id: UUID = Randoms.uuid()

    @Transient
    private var _isNew = true


    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other is HibernateProxy) return id == other.hibernateLazyInitializer.identifier
        if (this::class != other::class) return false
        return id == (other as Identifiable).id
    }

    override fun hashCode(): Int {
        return Objects.hashCode(id)
    }

    override fun toString(): String {
        return "${javaClass.simpleName}(id=$id)"
    }


    override fun getId(): UUID {
        return id
    }

    override fun isNew(): Boolean {
        return _isNew
    }

    @PostPersist
    @PostLoad
    private fun manage() {
        _isNew = false
    }

}
