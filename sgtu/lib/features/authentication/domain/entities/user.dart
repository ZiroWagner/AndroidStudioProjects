import 'package:equatable/equatable.dart';

class User extends Equatable {
  final int id;
  final String email;
  final String nombre;
  final String rol;
  final String? token;

  const User({
    required this.id,
    required this.email,
    required this.nombre,
    required this.rol,
    this.token,
  });

  @override
  List<Object?> get props => [id, email, nombre, rol, token];
}