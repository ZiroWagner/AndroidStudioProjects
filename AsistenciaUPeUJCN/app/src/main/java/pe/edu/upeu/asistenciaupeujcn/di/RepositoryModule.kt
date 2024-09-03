package pe.edu.upeu.asistenciaupeujcn.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import pe.edu.upeu.asistenciaupeujcn.repository.UsuarioRepository
import pe.edu.upeu.asistenciaupeujcn.repository.UsuarioRepositoryImp

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun userRepository(userRepos: UsuarioRepositoryImp): UsuarioRepository
}