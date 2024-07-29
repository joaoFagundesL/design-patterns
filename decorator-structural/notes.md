Inheritance also helps to expand the functionality but that is at compiletime not at runtime.
One of the best example of decorator is customized pizza. Where you have pizza and you have different decorator
like addExtraChese, cheeseburst, olives yes, jalepeno yes. We keep adding topups and we finally get pizza.

You can also think of Account -> Saving Account -> Salary Account -> Preferred Account.
Where base is Account now based on different accounttype it keeps adding new features and offers to same account.
To achieve decorator pattern you need following:
Create Interface - Base product
Create abstract class - Base product's base impl
Abstract Decorator - which implements base product - This works as foundation of decorator what other decorator needs to do minimum.
Different decorators - Which takes this product input and decorate it.

Instead of having multiple classes that define the type of pizza you just create the decorator of the pizza
and add to it.

The Decorator design pattern allows you to dynamically add behavior or responsibilities to objects without
modifying their code. Instead of creating a multitude of subclasses to achieve different combinations of features,
you create a set of decorator classes that are used to wrap concrete components
