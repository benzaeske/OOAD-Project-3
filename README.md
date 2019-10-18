# OOAD-Project-3
## Ben Zaeske, Peng Jiang, Mikayla Pickett

We developed our code in Eclipse using Java 8. To run it, clone this repository into your Eclipse workspace and run the main method found within 'Simulation.java'. Our output is captured in simulation.out

### Patterns Used

We used the Observer, Factory, and Decorator pattern throughout our code. 
- The Decorator pattern is documented in Tool.java, and is used to add options to Tool objects.
- The Observer pattern is documented in Customer.java and HardwareStore.java. For our observer pattern, we treated the HardwareStore object as a subject and Customer objects as observers of the HardwareStore.
- The Factory Pattern is documented in Tool.java and Customer.java. It is utilized in Simulation.java to generate Tool and Customer objects, as well as in Customer.java to to automate the wrapping of Tool objects in Option decorators.



