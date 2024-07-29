Composite pattern : Composite is a structural design pattern that lets you compose objects
into tree structures and then work with these structures as if they were individual objects.

It can be viewed as a tree structure made up of types that inherit a base type,
and it can represent a single part or a whole hierarchy of objects.

Component – is the base interface for all the objects in the composition. It should be either an interface
or an abstract class with the common methods to manage the child composites.
Leaf – implements the default behavior of the base component. It doesn't contain a reference to the other objects.
Composite – has leaf elements. It implements the base component methods and defines the child-related operations.

Imagine you have a Department hierarchy where certain departments can contain other departments. For instance, you might have
ITDepartment and SalesDepartment, which are leaf nodes in this hierarchy because they do not contain any other departments.
In contrast, HeadDepartment is a composite department that can hold multiple other departments within it.

In this case, you would create the HeadDepartment with a list of departments inside it instead of creating another classes.
Department is an interface that is implemented by every department.

```java
public class HeadDepartment implements Department{

    private Integer id;
    private String name;

    private List<Department> childDepartments;

    public HeadDepartment(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.childDepartments = new ArrayList<>();
    }

    public void printDepartmentName() {
        childDepartments.forEach(Department::printDepartmentName);
    }

    public void addDepartment(Department department) {
        childDepartments.add(department);
    }

    public void removeDepartment(Department department) {
        childDepartments.remove(department);
    }
}

public static void main(String[] args) {
		Department salesDepartment = new SalesDepartment(
				1, "Sales department");
		Department financialDepartment = new FinancialDepartment(
				2, "Financial department");

		HeadDepartment headDepartment = new HeadDepartment(
				3, "Head department");

		headDepartment.addDepartment(salesDepartment);
		headDepartment.addDepartment(financialDepartment);

		headDepartment.printDepartmentName();
	}
```
