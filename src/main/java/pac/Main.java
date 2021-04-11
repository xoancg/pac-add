package pac;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        // Configuración Hibernate
        System.out.println("Cargando a la configuración de Hibernate...");
        Configuration hibernateConfig = new Configuration().configure("hibernate.cfg.xml");

        // Conexión con la base de datos
        System.out.println("Iniciamos la conexión con la base de datos...");
        SessionFactory sessionFactory = hibernateConfig.buildSessionFactory();

        // Inicio sesión de usuario
        System.out.println("Abriendo sesión...");
        Session session = sessionFactory.openSession();

        // Creamos módulos
        Modulo modulo1 = new Modulo("Programacion B", "M03B");
        Modulo modulo2 = new Modulo("Acceso a Datos", "M06");
        Modulo modulo3 = new Modulo("Desarrollo de aplicaciones móviles", "M08");
        Modulo modulo4 = new Modulo("Servicios y procesos", "M09");

        Set<Modulo> set1 = new HashSet<>(); // Juan
        set1.add(modulo1);
        set1.add(modulo2);
        set1.add(modulo3);
        set1.add(modulo4);

        Set<Modulo> set2 = new HashSet<>(); // Pedro
        set2.add(modulo1);
        set2.add(modulo2);
        set2.add(modulo4);

        Set<Modulo> set3 = new HashSet<>(); // Marta
        set3.add(modulo3);
        set3.add(modulo4);

        Set<Modulo> set4 = new HashSet<>(); // Carla
        set4.add(modulo1);
        set4.add(modulo2);
        set4.add(modulo4);

        System.out.println("Creando módulos...");
        /*System.out.println(modulo1.toString()); // Programacion B
        System.out.println(modulo2.toString()); // Acceso a datos
        System.out.println(modulo3.toString()); // Desarrollo de aplicaciones móviles
        System.out.println(modulo4.toString()); // Servicios y procesos*/

        try {
            session.beginTransaction();
            session.save(modulo1);
            session.save(modulo2);
            session.save(modulo3);
            session.save(modulo4);
            session.getTransaction().commit();
        } catch (Throwable e) {
            System.out.println("Error al grabar módulo!");
            System.err.println(e.toString());
            throw new ExceptionInInitializerError("Error al inicializar! (1)");
        }

        // Módulos - Sentencias SQL
        /*try {
            session.beginTransaction();
            Long moduloId = modulo1.getId();
            System.out.println("Id = moduloId" + moduloId);
            Query<Modulo> queryModulo = session.createQuery("from Modulo where m.id = :moduloId");
            queryModulo.setParameter("moduloId", moduloId);
            System.out.println(queryModulo.getSingleResult());
            session.getTransaction().commit();
        } catch (Throwable ex) {
            System.out.println("Error al recuperar módulo!");
            System.err.println(ex.toString());
            throw new ExceptionInInitializerError("Error al inicializar! (2)");
        }*/

        // Creación de alumnos

        Alumno alumno1 = new Alumno("Juan", "Espaniola", 26, "Hombre", set1);
        Alumno alumno2 = new Alumno("Pedro", "Andorrana", 21, "Hombre", set2);
        Alumno alumno3 = new Alumno("Marta", "Espaniola", 19, "Mujer", set3);
        Alumno alumno4 = new Alumno("Juan", "Francesa", 35, "Mujer", set4);

        // Creación de alumnos y asignación de módulos

        System.out.println("Creando alumnos...");
/*        System.out.println(alumno1.toString()); // Juan
        System.out.println(alumno2.toString()); // Pedro
        System.out.println(alumno3.toString()); // Marta
        System.out.println(alumno4.toString()); // Carla*/

        try {
            session.beginTransaction();
            session.save(alumno1);
            session.save(alumno2);
            session.save(alumno3);
            session.save(alumno4);
            session.getTransaction().commit();
        } catch (Throwable e) {
            System.out.println("Error al grabar alumno!");
            System.err.println(e.toString());
            throw new ExceptionInInitializerError("Error al inicializar! (1)");
        }

        // Creamos profesores
        System.out.println("Creando profesor...");
        Profesor profesor = new Profesor("Alvaro", "Hombre");
        System.out.println(profesor.toString());

        try {
            session.beginTransaction();
            session.save(profesor);
            session.getTransaction().commit();
        } catch (Throwable e) {
            System.out.println("Error al grabar usuario!");
            System.err.println(e.toString());
            throw new ExceptionInInitializerError("Error al inicializar! (1)");
        }

        // Sentencias SQL
/*        try {
            session.beginTransaction();
            Long userId = profesor.getId();
            System.out.println("userId = " + userId);
            Query<Profesor> queryProfesor = session.createQuery("from Profesor p where p.id = :userId");
            queryProfesor.setParameter("userId", userId);
            System.out.println(queryProfesor.getSingleResult());
            session.getTransaction().commit();
        } catch (Throwable ex) {
            System.out.println("Error al recuperar usuario!");
            System.err.println(ex.toString());
            throw new ExceptionInInitializerError("Error al inicializar! (2)");
        }*/

        // Fin sesión de usuario
        System.out.println("Cerrando sesión...");
        session.close();

        // Desconexión de la base de datos
        System.out.println("Cerrando conexión...");
        sessionFactory.close();

    }
}

// https://examples.javacodegeeks.com/enterprise-java/hibernate/hibernate-maven-example/
// http://www.mastertheboss.com/jboss-frameworks/hibernate-jpa/maven-hibernate-jpa/maven-and-hibernate-4-tutorial
// https://docs.jboss.org/hibernate/stable/core.old/reference/en/html/configuration-xmlconfig.html
// https://dzone.com/articles/hibernate-5-xml-configuration-example