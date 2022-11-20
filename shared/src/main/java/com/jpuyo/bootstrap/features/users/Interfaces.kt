import com.jpuyo.bootstrap.domain.model.DomainError
import com.jpuyo.bootstrap.domain.model.Either
import com.jpuyo.bootstrap.features.users.domain.model.User

interface UsersRepository {
    suspend fun getUsers(): Either<DomainError, List<User>>
    suspend fun getUserDetail(id: String): Either<DomainError, User>
}

interface UsersRemoteDataSource {
    suspend fun getUsers(): Either<DomainError, List<User>>
    suspend fun getUserDetail(id: String): Either<DomainError, User>
}