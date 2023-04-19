package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data   // Refactor > DeLombok to undo
@EqualsAndHashCode(exclude = {"recipes"})   // exclude recipes property from @EqualsAndHashCode Lombok annotation to avoid stackoverflow error d/t javax.persistence @ManyToMany
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
