/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sce.employee;

import br.sce.util.GenericDAO;
import br.sce.util.HibernateUtil;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Pedro Saraiva
 */
public class EmployeeDAO extends GenericDAO<Employee>{

    public EmployeeDAO() {
        super(Employee.class);
    }
    
    public List<Employee> listMaster() {
        List<Employee> lista = null;
        try {
            this.setSessao(HibernateUtil.getSessionFactory().openSession());
            setTransacao(getSessao().beginTransaction());
            lista = this.getSessao().createCriteria(Employee.class).
                    add(Restrictions.eq("type", 'S')).list();
            
        } catch (Throwable e) {
            if (getTransacao().isActive()) {
                getTransacao().rollback();
            }
            JOptionPane.showMessageDialog(null, "Não foi possível listar: " + e.getMessage());
        } finally {
            getSessao().close();
        }
        return lista;
    }
    
    public List<Employee> listSupervised() {
        List<Employee> lista = null;
        try {
            this.setSessao(HibernateUtil.getSessionFactory().openSession());
            setTransacao(getSessao().beginTransaction());
            lista = this.getSessao().createCriteria(Employee.class).
                    add(Restrictions.eq("type", 'A')).list();
            
        } catch (Throwable e) {
            if (getTransacao().isActive()) {
                getTransacao().rollback();
            }
            JOptionPane.showMessageDialog(null, "Não foi possível listar: " + e.getMessage());
        } finally {
            getSessao().close();
        }
        return lista;
    }
    
    
    
}
