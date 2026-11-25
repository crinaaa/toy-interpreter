package interpreter.controller;

import interpreter.model.value.IValue;
import interpreter.model.value.RefValue;

import java.util.*;
import java.util.stream.Collectors;

public final class GarbageCollector {
    private GarbageCollector() {}

    public static Set<Integer> getAddrFromSymTable(Collection<IValue> symTableValues)
    {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> ((RefValue) v).address())
                .collect(Collectors.toSet());
    }  //returns the set of the addresses in the heap

    /* ---> this is the unsafe version!!

    public static Map<Integer, IValue> unsafeGarbageCollector(Set<Integer> symTableAddr,
                                                              Map<Integer, IValue> heap)
    {
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }  //this iterates through the heap and keeps only the entries whose keys
       //appear in the set computed above

    */

    // ---> this is the safe version!!
    public static Set<Integer> reachableAddresses(Set<Integer> roots, Map<Integer, IValue> heap) {
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> toVisit = new ArrayDeque<>(roots);
        while (!toVisit.isEmpty()) {
            int address = toVisit.pop();
            if (!heap.containsKey(address) || !visited.add(address))
                continue;                                  // null, dangling or already seen
            if (heap.get(address) instanceof RefValue ref)
                toVisit.push(ref.address());               // follow the reference
        }
        return visited;
    }   //we also keep track of the addressed that are referenced indirectly

    public static Map<Integer, IValue> safeGarbageCollector(Set<Integer> symTableAddr,
                                                            Map<Integer, IValue> heap) {
        Set<Integer> reachable = reachableAddresses(symTableAddr, heap);
        return heap.entrySet().stream()
                .filter(e -> reachable.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        //and now we do the mapping, similar to how we did it in the
        //initial(unsafe) version
    }
}