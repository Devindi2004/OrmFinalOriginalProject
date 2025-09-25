package org.example.ormfinalproject.BO.custom.impl;

import org.example.ormfinalproject.BO.custom.PaymentBO;
import org.example.ormfinalproject.Entity.Course;
import org.example.ormfinalproject.Entity.Payment;
import org.example.ormfinalproject.Entity.Student;
import org.example.ormfinalproject.dao.DAOFactory;
import org.example.ormfinalproject.dao.custom.CourseDAO;
import org.example.ormfinalproject.dao.custom.PaymentDAO;
import org.example.ormfinalproject.dao.custom.StudentDAO;
import org.example.ormfinalproject.model.PaymentDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentBOImpl implements PaymentBO {
    private final PaymentDAO paymentDAO =
            (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    private final StudentDAO studentDAO =
            (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final CourseDAO courseDAO =
            (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public boolean savePayment(PaymentDTO dto) throws Exception {
        Student student = studentDAO.findById(dto.getStudentId());
        Course course = courseDAO.findById(dto.getCourseId());

        // Convert String to SQL Date

        Payment payment = new Payment(

                dto.getPaymentDate(),
                dto.getPaymentMethod(),
                dto.getAmount(),
                student,
                course
        );

        System.out.println(payment + " payment");
        return paymentDAO.save(payment);
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws Exception {
        Student student = studentDAO.findById(dto.getStudentId());
        Course course = courseDAO.findById(dto.getCourseId());

        Payment payment = new Payment(
                dto.getPaymentId(),
                dto.getPaymentDate(),
                dto.getPaymentMethod(),
                dto.getAmount(),
                student,
                course
        );

        return paymentDAO.update(payment);
    }

    @Override
    public boolean deletePayment(String id) throws Exception {
        return paymentDAO.delete(Long.parseLong(id));
    }

    @Override
    public List<PaymentDTO> getAll() throws Exception {
        return paymentDAO.getAll().stream().map(payment ->
                new PaymentDTO(
                        payment.getPaymentId(),
                        payment.getPaymentDate(),   // ✅ Date (not String)
                        payment.getPaymentMethod(),
                        payment.getAmount(),        // ✅ double
                        payment.getStudent().getStudentId(),
                        payment.getCourse().getCourseId()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public ArrayList<PaymentDTO> getAllPayments() throws Exception {
        ArrayList<Payment> payments = (ArrayList<Payment>) paymentDAO.getAll();
        ArrayList<PaymentDTO> dtos = new ArrayList<>();

        for (Payment p : payments) {
            dtos.add(new PaymentDTO(
                    p.getPaymentId(),
                    p.getPaymentDate(),          // ✅ Date
                    p.getPaymentMethod(),
                    p.getAmount(),               // ✅ double
                    p.getStudent().getStudentId(),
                    p.getCourse().getCourseId()
            ));
        }

        return dtos;
    }

    @Override
    public List<String> getAllStudentIds() throws Exception {
        List<Student> list = studentDAO.getAll();
        List<String> idList = new ArrayList<>();
        for (Student s : list) {
            idList.add(String.valueOf(s.getStudentId()));
        }
        return idList;
    }

    @Override
    public List<String> getAllCourseIds() throws Exception {
        List<Course> list = courseDAO.getAll();
        List<String> idList = new ArrayList<>();
        for (Course c : list) {
            idList.add(String.valueOf(c.getCourseId()));
        }
        return idList;
    }
}
