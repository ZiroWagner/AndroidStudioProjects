import 'package:dartz/dartz.dart';
import 'package:dio/dio.dart';
import '../../../core/error/failures.dart';
import '../../domain/entities/user.dart';
import '../../domain/repositories/auth_repository.dart';
import '../datasources/auth_api_service.dart';

class AuthRepositoryImpl implements AuthRepository {
  final AuthApiService _apiService;

  AuthRepositoryImpl(this._apiService);

  @override
  Future<Either<Failure, User>> login(String email, String password) async {
    try {
      final response = await _apiService.login({
        'email': email,
        'password': password,
      });

      if (response.response.statusCode == 200) {
        return Right(response.data);
      } else {
        return Left(ServerFailure());
      }
    } on DioError catch (e) {
      if (e.response?.statusCode == 401) {
        return Left(AuthenticationFailure());
      }
      return Left(ServerFailure());
    } catch (e) {
      return Left(ServerFailure());
    }
  }

  @override
  Future<void> logout() async {
    // Implementar logout si es necesario
  }
}