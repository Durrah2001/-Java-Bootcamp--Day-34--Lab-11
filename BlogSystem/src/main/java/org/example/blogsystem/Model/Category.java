package org.example.blogsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @NotEmpty(message = "Category name can not be empty!")
    @Size(min= 4, max= 20, message = "Category name must be more than 3 and less than 21 letters!")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String name;

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(@NotEmpty(message = "Category name can not be empty!") @Size(min = 4, max = 20, message = "Category name must be more than 3 and less than 21 letters!") String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public @NotEmpty(message = "Category name can not be empty!") @Size(min = 4, max = 20, message = "Category name must be more than 3 and less than 21 letters!") String getName() {
        return name;
    }
}
