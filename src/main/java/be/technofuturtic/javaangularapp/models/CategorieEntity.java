package be.technofuturtic.javaangularapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Categorie", schema = "public", catalog = "javaangulardb")
public class CategorieEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorie_generator")
    @SequenceGenerator(name = "categorie_generator", allocationSize = 1, initialValue = 1)
    @Column(name = "id_categorie")
    private Integer idCategorie;

    @Column(name = "nom", nullable = false)
    private String nomCategorie;

    @Column(name = "description")
    private String descCategorie;

    @Column(name = "is_active", nullable = false)
    private boolean isActiveCategorie;

    public Integer getIdCategorie() {
        return idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getDescCategorie() {
        return descCategorie;
    }

    public void setDescCategorie(String descCategorie) {
        this.descCategorie = descCategorie;
    }

    public boolean isActiveCategorie() {
        return isActiveCategorie;
    }

    public void setActiveCategorie(boolean isActiveCategorie) {
        this.isActiveCategorie = isActiveCategorie;
    }

    @OneToMany(mappedBy = "categorie", targetEntity = DefiEntity.class, fetch = FetchType.LAZY)
    private List<DefiEntity> defis;

    @OneToMany(mappedBy = "categorie", targetEntity = ParcoursEntity.class, fetch = FetchType.LAZY)
    private List<ParcoursEntity> parcours;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategorieEntity that = (CategorieEntity) o;
        return Objects.equals(nomCategorie, that.nomCategorie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategorie, nomCategorie, descCategorie, isActiveCategorie, defis, parcours);
    }

    public CategorieEntity(String nomCategorie, String descCategorie) {
        this();
        this.nomCategorie = nomCategorie;
        this.descCategorie = descCategorie;
    }

    public CategorieEntity() {
        this.parcours = new ArrayList<>();
        this.defis = new ArrayList<>();
        this.isActiveCategorie =  true;
    }

    @Override
    public String toString() {
        return "CategorieEntity{" +
                "idCategorie=" + idCategorie +
                ", nomCategorie='" + nomCategorie + '\'' +
                ", descCategorie='" + descCategorie + '\'' +
                ", isActiveCategorie=" + isActiveCategorie +
                '}';
    }
}
