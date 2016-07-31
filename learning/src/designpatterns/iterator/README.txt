The Iterator provides a way to access the elements of an aggregate object without exposing its underlying representation.

Like iterating two different containers (menus, inventory) that implement their collections differently; can have a
MenuIterator and InventoryIterator that both implement Iterator for hasNext() and next().