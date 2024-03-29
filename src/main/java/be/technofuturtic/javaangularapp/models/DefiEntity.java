package be.technofuturtic.javaangularapp.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Defi", schema = "public", catalog = "javaangulardb")
public class DefiEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "defi_generator")
    @SequenceGenerator(name = "defi_generator", allocationSize = 1, initialValue = 1)
    @Column(name = "id_defi")
    private Integer idDefi;

    @Column(name = "nom", nullable = false, unique = true)
    private String nomDefi;

    @Column(name = "description", nullable = false)
    private String descDefi;

    @Column(name = "infobulle")
    private String infobulleDefi;

    @Column(name = "is_active", nullable = false)
    private boolean isActiveDefi;

    public Integer getIdDefi() {
        return idDefi;
    }

    public String getNomDefi() {
        return nomDefi;
    }

    public void setNomDefi(String nomDefi) {
        this.nomDefi = nomDefi;
    }

    public String getDescDefi() {
        return descDefi;
    }

    public void setDescDefi(String descDefi) {
        this.descDefi = descDefi;
    }

    public String getInfobulleDefi() {
        return infobulleDefi;
    }

    public void setInfobulleDefi(String infobulleDefi) {
        this.infobulleDefi = infobulleDefi;
    }

    public boolean isActiveDefi() {
        return isActiveDefi;
    }

    public void setActiveDefi(boolean isActiveDefi) {
        this.isActiveDefi = isActiveDefi;
    }

    public void setCategorie(CategorieEntity categorie) {
        this.categorie = categorie;
    }

    public CategorieEntity getCategorie() {
        return categorie;
    }

    @ManyToOne(targetEntity = CategorieEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorie", referencedColumnName = "id_categorie", foreignKey = @ForeignKey(name = "FK_Defi_Categorie"))
    private CategorieEntity categorie;

    @ManyToMany (mappedBy = "listeDefis", fetch = FetchType.LAZY)
    private List<ParcoursEntity> listeParcours;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefiEntity that = (DefiEntity) o;
        return  Objects.equals(nomDefi, that.nomDefi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDefi, nomDefi, descDefi, infobulleDefi, isActiveDefi, categorie);
    }

    public DefiEntity(String nomDefi, String descDefi, String infobulleDefi, CategorieEntity categorie) {
        this();
        this.nomDefi = nomDefi;
        this.descDefi = descDefi;
        this.infobulleDefi = infobulleDefi;
        this.categorie = categorie;
    }

    public DefiEntity() {
        this.listeParcours = new ArrayList<ParcoursEntity>();
        this.isActiveDefi = true;
    }

    public DefiEntity(String nomDefi, String descDefi, String infobulleDefi, Optional<CategorieEntity> categorieEntityOptional) {
        this();
        this.nomDefi = nomDefi;
        this.descDefi = descDefi;
        this.infobulleDefi = infobulleDefi;
        this.categorie = categorieEntityOptional.get();
    }

    @Override
    public String toString() {
        return "DefiEntity{" +
                "idDefi=" + idDefi +
                ", nomDefi='" + nomDefi + '\'' +
                ", descDefi='" + descDefi + '\'' +
                ", infobulleDefi='" + infobulleDefi + '\'' +
                ", isActiveDefi=" + isActiveDefi +
                ", catégorie= " + categorie +
                '}';
    }
}
