# Iterator Pattern
Why Iterator is preferable to simply exposing List<Episode>?

• Encapsulation: clients don't know how the episodes are stored (ArrayList, LinkedList, or even a lazy loader from a file).

• Flexibility: you can change the way the traversal works without changing the client code.

• Security: prevents incorrect modification of the collection from the outside.

• Extensibility: it's easy to add new types of traversal (e.g. "shuffle" or "skip intro").


# Mediator Pattern
Why is Mediator better than aircraft talking directly?

• Better scalability: When more planes are added, their code for communicating with others does not need to be changed - only through the tower.

• Centralization of logic: All rules (for example, emergency landing priorities) are in one place - in the ControlTower.

• Easier to modify: You can easily change the rules of interaction without touching all the planes.


Disadvantage:

• Mediator can become a "God Object" - if you stuff too much logic into the tower, it becomes complex and difficult to maintain.
