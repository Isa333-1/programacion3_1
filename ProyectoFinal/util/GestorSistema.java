package ProyectoFinal.util;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import ProyectoFinal.estructuras.ContenedorEstructuras;
import ProyectoFinal.estructuras.Operacion;
import ProyectoFinal.excepciones.ExcepcionesSistema.ArchivoInvalidoException;
import ProyectoFinal.excepciones.ExcepcionesSistema.ColaDeEsperaVaciaException;
import ProyectoFinal.excepciones.ExcepcionesSistema.CupoLlenoException;
import ProyectoFinal.excepciones.ExcepcionesSistema.EstudianteNoEncontradoException;
import ProyectoFinal.excepciones.ExcepcionesSistema.HorarioConflictivoException;
import ProyectoFinal.excepciones.ExcepcionesSistema.PreRequisitoNoAprobadoException;
import ProyectoFinal.modelo.Aula;
import ProyectoFinal.modelo.Campus;
import ProyectoFinal.modelo.Estudiante;
import ProyectoFinal.modelo.Materia;

public class GestorSistema
{
    private Scanner sc;
    private ContenedorEstructuras estructuras;
    private Campus campus;
 
    public GestorSistema(Scanner sc) throws ArchivoInvalidoException, IOException
    {
        this.sc = sc;
        this.estructuras = new ContenedorEstructuras();
        String[] edificios = {"Ingenieria", "Biblioteca", "Cafeteria", "Rectoria", "Laboratorios"};
        this.campus = new Campus(edificios);
 
        // Carga automatica de estudiantes al iniciar el sistema
        cargarEstudiantesCSV("/workspaces/programacion3_1/ProyectoFinal/estudiantes.csv");
        cargarMateriasCSV("/workspaces/programacion3_1/ProyectoFinal/materias.csv");
    }

    public void registrarEstudiante()
    {
        System.out.println("\nREGISTRO DE ESTUDIANTE");
        System.out.print("ID: ");
        String id = sc.nextLine().trim();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Email: ");
        String email = sc.nextLine().trim();
        System.out.print("Semestre actual: ");
        int semestre = Integer.parseInt(sc.nextLine().trim());
 
        Estudiante nuevo = new Estudiante(nombre, id, email, semestre);
        estructuras.getEstudiantesMap().put(id, nuevo);
        System.out.println("Estudiante registrado exitosamente.");
    }

    public void buscarEstudiantePorId() throws EstudianteNoEncontradoException
    {
        System.out.println("\nBUSCAR ESTUDIANTE");
        System.out.print("ID: ");
        String id = sc.nextLine().trim();
        Estudiante est = estructuras.getEstudiantesMap().get(id);
        if (est == null)
        {
            throw new EstudianteNoEncontradoException("No existe estudiante con ID: " + id);
        }
        System.out.println("Resultado encontrado:");
        est.mostrarInformacion();
    }

      public void listarEstudiantes()
    {
        System.out.println("\nLISTA DE ESTUDIANTES");
        Map<String, Estudiante> mapa = estructuras.getEstudiantesMap();
        if (mapa.isEmpty())
        {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
 
        for (Map.Entry<String, Estudiante> entry : mapa.entrySet())
        {
            entry.getValue().mostrarInformacion();
            System.out.println("------------------------------------------");
        }
    }

    public void eliminarEstudiante() throws EstudianteNoEncontradoException
    {
        System.out.println("\nELIMINAR ESTUDIANTE");
        System.out.print("ID: ");
        String id = sc.nextLine().trim();
        Estudiante est = estructuras.getEstudiantesMap().get(id);
        if (est == null)
        {
            throw new EstudianteNoEncontradoException("No existe estudiante con ID: " + id);
        }
        estructuras.getEstudiantesMap().remove(id);
        estructuras.getPilaDeshacer().push(Operacion.crearOperacionEliminarEstudiante(est));
        estructuras.getPilaRehacer().clear();
        System.out.println("Estudiante eliminado.");
    }

    public void crearMateria()
    {
        System.out.println("\nCREAR MATERIA");
        System.out.print("Codigo: ");
        String codigo = sc.nextLine().trim();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Cupos maximos: ");
        int cupos = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Creditos: ");
        int creditos = Integer.parseInt(sc.nextLine().trim());
 
        Materia materia = new Materia(codigo, nombre, cupos, creditos);
        estructuras.getMateriasMap().put(codigo, materia);
        System.out.println("Materia creada exitosamente.");
    }

    public void agregarPreRequisito()
    {
        System.out.print("Codigo de la materia: ");
        String codMateria = sc.nextLine().trim();
        System.out.print("Codigo del pre-requisito: ");
        String codPre = sc.nextLine().trim();
 
        Materia mat = estructuras.getMateriasMap().get(codMateria);
        Materia pre = estructuras.getMateriasMap().get(codPre);
        String msg = (mat != null && pre != null) ? "Pre-requisito agregado." : "Materia o pre-requisito no encontrado.";
        if (mat != null && pre != null)
        {
            mat.agregarPreRequisito(pre);
        }
        System.out.println(msg);
    }

    public void mostrarPreRequisitos()
    {
        System.out.print("Codigo de la materia: ");
        String codMateria = sc.nextLine().trim();
        Materia mat = estructuras.getMateriasMap().get(codMateria);
        if (mat == null)
        {
            System.out.println("Materia no encontrada.");
            return;
        }
        System.out.println("Pre-requisitos de " + mat.getCodigo() + " - " + mat.getNombre() + ":");
        if (mat.getPreRequisitos().isEmpty())
        {
            System.out.println("Ninguno.");
            return;
        }
        for (int i = 0; i < mat.getPreRequisitos().size(); i++)
        {
            Materia pre = mat.getPreRequisitos().get(i);
            System.out.println("- " + pre.getCodigo() + ": " + pre.getNombre());
        }
    }

    public void inscribirEstudiante() throws EstudianteNoEncontradoException, PreRequisitoNoAprobadoException, CupoLlenoException
    {
        System.out.print("ID Estudiante: ");
        String idEst = sc.nextLine().trim();
        System.out.print("Codigo Materia: ");
        String codMat = sc.nextLine().trim();
 
        Estudiante est = estructuras.getEstudiantesMap().get(idEst);
        if (est == null)
        {
            throw new EstudianteNoEncontradoException("No existe estudiante con ID: " + idEst);
        }
        Materia mat = estructuras.getMateriasMap().get(codMat);
        if (mat == null)
        {
            System.out.println("Materia no encontrada.");
            return;
        }
        for (int i = 0; i < mat.getPreRequisitos().size(); i++)
        {
            Materia pre = mat.getPreRequisitos().get(i);
            if (!est.tieneMateriaAprobada(pre.getCodigo()))
            {
                throw new PreRequisitoNoAprobadoException("Falta aprobar pre-requisito: " + pre.getCodigo());
            }
        }
        if (mat.estaLlena())
        {
            mat.encolarEstudiante(est);
            throw new CupoLlenoException("Materia llena. Agregada a COLA DE ESPERA");
        }
        mat.inscribirDirecto(est);
        estructuras.getPilaDeshacer().push(new Operacion("INSCRIPCION", est.getId(), mat.getCodigo()));
        estructuras.getPilaRehacer().clear();
        System.out.println("Cupo disponible (cupos restantes: " + mat.getCuposRestantes() + ")");
    }

    public void cancelarInscripcion() throws EstudianteNoEncontradoException, ColaDeEsperaVaciaException, CupoLlenoException
    {
        System.out.print("ID Estudiante: ");
        String idEst = sc.nextLine().trim();
        System.out.print("Codigo Materia: ");
        String codMat = sc.nextLine().trim();
 
        Estudiante est = estructuras.getEstudiantesMap().get(idEst);
        if (est == null)
        {
            throw new EstudianteNoEncontradoException("No existe estudiante con ID: " + idEst);
        }
        Materia mat = estructuras.getMateriasMap().get(codMat);
        if (mat == null)
        {
            System.out.println("Materia no encontrada.");
            return;
        }
        mat.cancelarInscripcion(est);
        estructuras.getPilaDeshacer().push(new Operacion("CANCELACION", est.getId(), mat.getCodigo()));
        estructuras.getPilaRehacer().clear();
        System.out.println("Cancelacion exitosa. Cupo liberado.");
 
        if (!mat.getColaEspera().isEmpty())
        {
            Estudiante siguiente = mat.desencolarSiguiente();
            mat.inscribirDirecto(siguiente);
            System.out.println("Asignando cupo a " + siguiente.getNombre() + " (primer estudiante en cola)");
        }
    }

      public void mostrarColaEspera()
    {
        System.out.print("Codigo Materia: ");
        String codMat = sc.nextLine().trim();
        Materia mat = estructuras.getMateriasMap().get(codMat);
        if (mat == null)
        {
            System.out.println("Materia no encontrada.");
            return;
        }
        System.out.println("MOSTRAR COLA DE ESPERA");
        int pos = 1;
        for (Estudiante est : mat.getColaEspera())
        {
            System.out.println("Posicion " + pos + ": " + est.getNombre());
            pos++;
        }
        System.out.println("Total en espera: " + mat.getColaEspera().size());
    }

    public void reservarHorario() throws HorarioConflictivoException
    {
        System.out.println("\nRESERVAR HORARIO EN AULA");
        System.out.print("Aula: ");
        String numAula = sc.nextLine().trim();
        System.out.print("Dia (0=Dom..6=Sab, 1=Lunes): ");
        int dia = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Hora (0-23): ");
        int hora = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Duracion: ");
        int duracion = Integer.parseInt(sc.nextLine().trim());
 
        Aula aula = estructuras.getAulasMap().get(numAula);
        if (aula == null)
        {
            aula = new Aula(numAula, 30);
            estructuras.getAulasMap().put(numAula, aula);
        }
 
        System.out.println("Verificando disponibilidad...");
        for (int h = hora; h < hora + duracion; h++)
        {
            String estado = aula.consultarDisponibilidad(dia, h) ? "LIBRE" : "OCUPADO";
            System.out.println("Dia " + dia + " " + h + ":00 -> " + estado);
        }
        aula.reservar(dia, hora, duracion);
        estructuras.getPilaDeshacer().push(Operacion.crearOperacionHorario(numAula, dia, hora, duracion));
        estructuras.getPilaRehacer().clear();
        System.out.println("Reserva exitosa.");
    }

}