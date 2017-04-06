/**
 * This file was generated by the JPA Modeler
 */ 

package ru.ilb.jparestresource.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.eclipse.persistence.annotations.Customizer;
import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@Entity
    @Customizer(ru.ilb.jparestresource.persistance.HistoryCustomizer.class)
public class Document extends DocumentBase implements Serializable { 

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @XmlElement
    @OneToMany(cascade={CascadeType.ALL},targetEntity = Docfile.class,mappedBy = "document")
    private List<Docfile> docfiles;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Docfile> getDocfiles() {
        return this.docfiles;
    }

    public void setDocfiles(List<Docfile> docfiles) {
        this.docfiles = docfiles;
    }


}
