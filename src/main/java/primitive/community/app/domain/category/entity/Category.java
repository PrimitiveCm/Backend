package primitive.community.app.domain.category.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import primitive.community.app.domain.category.dto.CategoryDto;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false)
    @Comment("이름")
    private String name;

    @Column(nullable = false)
    @Comment("계층")
    private int depth;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "upperCategoryId")
    private Category upperCategory;

    public Category(String name, int depth, Category upperCategory) {
        this.name = name;
        this.depth = depth;
        this.upperCategory = upperCategory;
    }

    public void updateCategory(String categoryName, int depth, Category upperCategory) {
        this.name = categoryName;
        this.depth = depth;
        this.upperCategory = upperCategory;
    }
}
