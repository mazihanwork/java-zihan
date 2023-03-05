package jp.co.axa.apidemo.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@EqualsAndHashCode
@Table(name="EMPLOYEE")
public class Employee {

    @Getter
    @Setter
    @Id
    @NotNull(message = "Id is required.")
    @Min(message = "Id should be larger than or equals to 0.", value = 0)
    private Long id;

    @Getter
    @Setter
    @Column(name="EMPLOYEE_NAME")
    @NotBlank(message = "Name cannot be blank.")
    private String name;

    @Getter
    @Setter
    @Column(name="EMPLOYEE_SALARY")
    private Integer salary;

    @Getter
    @Setter
    @Column(name="DEPARTMENT")
    private String department;

}
