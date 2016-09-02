/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugenisolucoes.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author cpo-202
 */
@Entity
@Table(name="dados")
public class Dado implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    private String engine;

    private String browser;

    private String platform;

    private String version;

    private String grade;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine( String engine ) {
        this.engine = engine;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser( String browser ) {
        this.browser = browser;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform( String platform ) {
        this.platform = platform;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion( String version ) {
        this.version = version;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade( String grade ) {
        this.grade = grade;
    }
}
