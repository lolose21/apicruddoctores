package models;

public class Doctor {

    private int hospitalcod;
    private int doctorno;
    private String apellido;
    private String especialidad;
    private int salario;

    public Doctor() {
    }

    public Doctor(int hospitalcod, int doctorno, String apellido, String especialidad, int salario) {
        this.hospitalcod = hospitalcod;
        this.doctorno = doctorno;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.salario = salario;
    }

    public int getHospitalcod() {
        return hospitalcod;
    }

    public void setHospitalcod(int hospitalcod) {
        this.hospitalcod = hospitalcod;
    }

    public int getDoctorno() {
        return doctorno;
    }

    public void setDoctorno(int doctorno) {
        this.doctorno = doctorno;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

}
