# Java Arrays
- [[An **array** is a collection of variables of the same type]], referred to
  by a common name.
- In Java, arrays are objects, and must be created dynamically (at runtime).
- [[Some important [brackets]
- :::
- works as expected! ]]

## Declaring an Array
- [[General Form: type[] arrayName;]]
- ex: int[] myData;

- The above only creates a reference to an array object, but no array has
  actually been created yet.
- [[Data types of the reference and array need to match.]]
    - [    [      [Why [[Why should data types of arrays need to match?:::
    - Because the reference is pointing to the array object.]]

## Creating an Array (Instantiation)
- [[General form:  arrayName = new type[numberOfElements];]]
- ex: myData = new int[100];

- Data types of the reference and array need to match.
- [[should numberOfElements be a Positive Integer or a Negative Int?:::Positive Integer.]]
- [[Gotcha: Array size
     is not modifiable once instantiated. ]]

... more brilliance captured... 