import 'package:retrofit/retrofit.dart';
import 'package:dio/dio.dart';
import '../models/user_model.dart';

part 'auth_api_service.g.dart';

@RestApi(baseUrl: "https://sgtu-backend.vercel.app/api")
abstract class AuthApiService {
  factory AuthApiService(Dio dio) = _AuthApiService;

  @POST("/usuarios/login")
  Future<HttpResponse<UserModel>> login(@Body() Map<String, dynamic> body);
}