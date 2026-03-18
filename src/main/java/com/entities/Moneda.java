package com.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "monedas")
public class Moneda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    // Agregamos TEXT también a la descripción por si escribís mucho
    @Column(columnDefinition = "TEXT")
    private String description;

    private String info;
    private Double price;
    private Double saleForGroup;

    // Usamos @Lob y una longitud grande para que las fotos en Base64 entren completas
    @Lob
    @Column(name = "image_front", length = 1000000)
    private String imageFront;

    @Lob
    @Column(name = "image_back", length = 1000000)
    private String imageBack;

    public Moneda() {}

    public Moneda(String name, String type, String description, String info, Double price, Double saleForGroup, String imageFront, String imageBack) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.info = info;
        this.price = price;
        this.saleForGroup = saleForGroup;
        this.imageFront = imageFront;
        this.imageBack = imageBack;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getInfo() { return info; }
    public void setInfo(String info) { this.info = info; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Double getSaleForGroup() { return saleForGroup; }
    public void setSaleForGroup(Double saleForGroup) { this.saleForGroup = saleForGroup; }

    public String getImageFront() { return imageFront; }
    public void setImageFront(String imageFront) { this.imageFront = imageFront; }

    public String getImageBack() { return imageBack; }
    public void setImageBack(String imageBack) { this.imageBack = imageBack; }
}