import 'package:get_it/get_it.dart';
import 'package:dio/dio.dart';
import 'package:pretty_dio_logger/pretty_dio_logger.dart';
import 'features/authentication/data/datasources/auth_api_service.dart';
import 'features/authentication/data/repositories/auth_repository_impl.dart';
import 'features/authentication/domain/repositories/auth_repository.dart';
import 'features/authentication/presentation/bloc/auth_bloc.dart';

final sl = GetIt.instance;

Future<void> init() async {
  // Bloc
  sl.registerFactory(
        () => AuthBloc(sl()),
  );

  // Repositories
  sl.registerLazySingleton<AuthRepository>(
        () => AuthRepositoryImpl(sl()),
  );

  // Data sources
  sl.registerLazySingleton(
        () => AuthApiService(sl()),
  );

  // External
  sl.registerLazySingleton(() {
    final dio = Dio();
    dio.interceptors.add(PrettyDioLogger());
    return dio;
  });
}