The Command pattern enables you to decouple the requestor of an action (like a remote control) from the object that
actually performs the action (like turn on a light).

It encapsulates a request as an object, thereby letting you parameterize other objects with different requests,
enqueue or log requests, and support undoable operations.

For appliances with more than just on/off, like a ceiling fan, we'd have a Command for each state, such as:
CeilingFanOnCommand (with default setting of MEDIUM), CeilingFanLowCommand, CeilingFanMediumCommand, CeilingFanHighCommand.
The 'undo' command would keep track of previous state (eg, HIGH) and restore that.