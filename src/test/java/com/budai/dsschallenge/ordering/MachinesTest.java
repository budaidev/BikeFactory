package com.budai.dsschallenge.ordering;

import com.budai.dsschallenge.dto.MachineUsage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MachinesTest {

    Machines machines;

    @BeforeEach
    public void setup() {
        machines = new Machines();

    }

    @Test
    public void testCuttingSimple() {
        int[] result = machines.scheduleTask(TaskType.CUT, "MEGR001", 1, 5, new int[1]);
        assertEquals(5, result[0]);
    }

    @Test
    public void testCutting() {
        int[] result = machines.scheduleTask(TaskType.CUT, "MEGR001", 1000, 5, new int[1000]);
        assertEquals(835, result[999]);
    }

    @Test
    public void scheduleAllTask() {
        int[] result = machines.scheduleAllTaskInOrder("ABC123", 1, List.of(5, 10, 8, 5, 12, 10));
        assertEquals(50, result[0]);

    }

    @Test
    public void scheduleAllTaskSix() {
        int[] result = machines.scheduleAllTaskInOrder("ABC123", 6, List.of(5, 10, 8, 5, 12, 10));
        for (MachineUsage use : machines.getLog()) {
            System.out.println(use);
        }
        assertEquals(75, result[5]);

    }

    @Test
    public void scheduleAllTaskThreeTwotimes() {
        machines.scheduleAllTaskInOrder("ABC123", 3, List.of(5, 10, 8, 5, 12, 10));
        int[] result = machines.scheduleAllTaskInOrder("ABC456", 3, List.of(5, 10, 8, 5, 12, 10));
        for (MachineUsage use : machines.getLog()) {
            System.out.println(use);
        }
        printGantDiagram(machines.getLog());
        assertEquals(75, result[2]);

    }

    public void printGantDiagram(List<MachineUsage> usage) {
        Map<String, List<Integer>> map = new HashMap<>();

        for (MachineUsage m : usage) {
            if (!map.containsKey(m.getMachineId())) {
                map.put(m.getMachineId(), new ArrayList<Integer>());
            }
            map.get(m.getMachineId()).addAll(IntStream.range(m.getStartTime(), m.getEndTime()).boxed().collect(Collectors.toList()));
        }

        List<String> machines = new ArrayList<>();
        machines.addAll(IntStream.range(0, 6).mapToObj(x -> Machines.getMachineName(TaskType.CUT, x + 1)).collect(Collectors.toList()));
        machines.addAll(IntStream.range(0, 2).mapToObj(x -> Machines.getMachineName(TaskType.BEND, x + 1)).collect(Collectors.toList()));
        machines.addAll(IntStream.range(0, 3).mapToObj(x -> Machines.getMachineName(TaskType.WELD, x + 1)).collect(Collectors.toList()));
        machines.addAll(IntStream.range(0, 1).mapToObj(x -> Machines.getMachineName(TaskType.TEST, x + 1)).collect(Collectors.toList()));
        machines.addAll(IntStream.range(0, 4).mapToObj(x -> Machines.getMachineName(TaskType.PAINT, x + 1)).collect(Collectors.toList()));
        machines.addAll(IntStream.range(0, 3).mapToObj(x -> Machines.getMachineName(TaskType.PACK, x + 1)).collect(Collectors.toList()));
        for (String machine : machines) {
            List<Integer> list = map.get(machine);
            int max = Collections.max(list);
            String line = IntStream.range(0, max + 1).mapToObj(x -> getGannt(list, x, max)).collect(Collectors.joining());
            System.out.println(String.format("%12s => ", machine) + line);
        }

    }

    private String getGannt(List<Integer> list, int x, int max) {
        if (x == max) {
            return "|";
        } else if (list.contains(x)) {
            return "x";
        } else {
            return " ";
        }
    }


}
