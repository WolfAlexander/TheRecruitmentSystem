package jobApplicationApp.entity.localized;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Languages we translate to
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "languages")
public class LanguageEntity {


    /**
     * The primary key to the language
     * @return id of language
     */
    @Id
    private Integer id;


    /**
     * A shortening of the language name, in two letters.
     * @return language name
     */
    @NotNull
    private String name;
}
