package ProyectoFinal.modelo;
 
public class Profesor extends Persona
{
    private String departamento;
    private double salario;
 
    public Profesor(String nombre, String id, String email, String departamento, double salario)
    {
        super(nombre, id, email);
        this.departamento = departamento;
        this.salario = salario;
    }
 
    public String getDepartamento()
    {
        return departamento;
    }
 
    public double getSalario()
    {
        return salario;
    }
 
    public void setDepartamento(String departamento)
    {
        this.departamento = departamento;
    }
 
    public void setSalario(double salario)
    {
        this.salario = salario;
    }
 
    @Override
    public void mostrarInformacion()
    {
        String salarioFmt = (salario >= 0) ? String.format("%.2f", salario) : "0.00";
        System.out.println("Profesor | ID: " + getId() + " | Nombre: " + getNombre()
                            + " | Email: " + getEmail() + " | Departamento: " + departamento
                            + " | Salario: " + salarioFmt);
    }
}