part of 'actividad_bloc.dart';

@immutable
sealed class ActividadEvent {}

class ListarActividadEvent extends ActividadEvent{
  ListarActividadEvent(){print("Evento si");}
}
class DeleteActividadEvent extends ActividadEvent{
  ActividadModeloFire actividad;
  DeleteActividadEvent(this.actividad);
}
class UpdateActividadEvent extends ActividadEvent{
  ActividadModeloFire actividad;
  UpdateActividadEvent(this.actividad);
}
class CreateActividadEvent extends ActividadEvent{
  ActividadModeloFire actividad;
  CreateActividadEvent(this.actividad);
}
