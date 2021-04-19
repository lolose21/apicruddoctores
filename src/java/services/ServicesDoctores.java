package services;

import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import models.Doctor;
import repositories.RepositoryDoctores;

@Path("/doctores")
public class ServicesDoctores {

    RepositoryDoctores repo;

    public ServicesDoctores() {
        this.repo = new RepositoryDoctores();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getDoctores() throws SQLException {
        List<Doctor> doctores = this.repo.getDoctor();
        Gson converter = new Gson();
        String json = converter.toJson(doctores);
        return json;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String findDoctorId(@PathParam("id") String id) throws SQLException {
        int iddoctor = Integer.parseInt(id);
        Doctor doctor = this.repo.findDoctorId(iddoctor);
        Gson converter = new Gson();
        String json = converter.toJson(doctor);
        return json;

    }

    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("hospitalcod/{id}")
    public List<Doctor> findDoctorIdhospital(@PathParam("id") String id) throws SQLException {
        int idhospital = Integer.parseInt(id);
        List<Doctor> doctor = this.repo.findDoctorIdhospital(idhospital);
        Gson converter = new Gson();
        String json = converter.toJson(doctor);
        return json;

    }*/

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/post")
    public Response insertarDoctor(String doctorjson) throws SQLException {
        Gson converter = new Gson();
        Doctor doctor
                = converter.fromJson(doctorjson, Doctor.class);
        this.repo.insertarDoctor(doctor.getHospitalcod(), doctor.getDoctorno(),
                doctor.getApellido(), doctor.getEspecialidad(), doctor.getSalario());
        return Response.status(200).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/put")
    public Response modificarDoctor(String doctorjson) throws SQLException {
        Gson converter = new Gson();
        Doctor doctor
                = converter.fromJson(doctorjson, Doctor.class);
        this.repo.modificarDoctor(doctor.getHospitalcod(), doctor.getDoctorno(),
                doctor.getApellido(), doctor.getEspecialidad(), doctor.getSalario());
        return Response.status(200).build();
    }

    @DELETE
    @Path("/delete/{iddoctorno}")
    public Response eliminarDoctor(@PathParam("iddoctorno") String iddoctorno) throws SQLException {
        int iddoctor = Integer.parseInt(iddoctorno);
        this.repo.eliminarDoctor(iddoctor);
        return Response.status(200).build();
    }
}
