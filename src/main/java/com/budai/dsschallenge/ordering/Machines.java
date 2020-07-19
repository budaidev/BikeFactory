package com.budai.dsschallenge.ordering;

import com.budai.dsschallenge.configuration.MachineNumber;
import com.budai.dsschallenge.dto.MachineUsage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

public class Machines {

    private PriorityQueue<Machine> cutting;
    private PriorityQueue<Machine> bending;
    private PriorityQueue<Machine> welding;
    private PriorityQueue<Machine> testing;
    private PriorityQueue<Machine> painting;
    private PriorityQueue<Machine> packing;

    private List<MachineUsage> usageLog;

    public static final Map<TaskType, String> machineName;

    static {
        Map<TaskType, String> m = new HashMap<>();
        m.put(TaskType.BEND, "Hajlito");
        m.put(TaskType.CUT, "Vago");
        m.put(TaskType.WELD, "Hegeszto");
        m.put(TaskType.TEST, "Tesztelo");
        m.put(TaskType.PAINT, "Festo");
        m.put(TaskType.PACK, "Csomagolo");

        machineName = Collections.unmodifiableMap(m);
    }

    public Machines() {
        cutting = init(MachineNumber.CUTTING);
        bending = init(MachineNumber.BENDING);
        welding = init(MachineNumber.WELDING);
        testing = init(MachineNumber.TESTING);
        painting = init(MachineNumber.PAINTING);
        packing = init(MachineNumber.PACKING);

        usageLog = new ArrayList<>();
    }

    private PriorityQueue<Machine> init(int capacity) {
        PriorityQueue<Machine> pq = new PriorityQueue<>(capacity);
        for (int i = 0; i < capacity; i++) {
            pq.add(new Machine(0, i + 1));
        }
        return pq;
    }

    private PriorityQueue<Machine> getMachineQueue(TaskType type) throws IllegalArgumentException {
        switch (type) {
            case CUT:
                return cutting;
            case BEND:
                return bending;
            case PAINT:
                return painting;
            case PACK:
                return packing;
            case WELD:
                return welding;
            case TEST:
                return testing;
        }
        throw new IllegalArgumentException("Unknown task type");

    }

    public TaskTime scheduleAllTaskInOrder(String orderId, int quantity, List<Integer> joblengths) {
        int[] cut = scheduleTask(TaskType.CUT, orderId, quantity, joblengths.get(0), new int[quantity]);
        int[] bend = scheduleTask(TaskType.BEND, orderId, quantity, joblengths.get(1), cut);
        int[] weld = scheduleTask(TaskType.WELD, orderId, quantity, joblengths.get(2), bend);
        int[] test = scheduleTask(TaskType.TEST, orderId, quantity, joblengths.get(3), weld);
        int[] paint = scheduleTask(TaskType.PAINT, orderId, quantity, joblengths.get(4), test);
        int[] pack = scheduleTask(TaskType.PACK, orderId, quantity, joblengths.get(5), paint);
        return new TaskTime(Arrays.stream(cut).min().getAsInt() - joblengths.get(0), Arrays.stream(pack).max().getAsInt());
    }

    public int[] scheduleTask(TaskType task, String orderId, int quantity, int joblength, int startTime[]) {
        int[] endtimes = new int[quantity];
        for (int i = 0; i < startTime.length; i++) {
            endtimes[i] = updateMachineJob(task, joblength, startTime[i], orderId);
        }
        return endtimes;
    }

    public static String getMachineName(TaskType type, int rank) {
        return machineName.get(type) + "-" + rank;
    }

    private int updateMachineJob(TaskType task, int joblength, int startTime, String orderId) {
        PriorityQueue<Machine> machines = getMachineQueue(task);
        Machine machine = machines.poll();
        int oldTime = Math.max(machine.getEndtime(), startTime);
        int newEndTime = oldTime + joblength;
        machines.add(new Machine(newEndTime, machine.getRank()));
        usageLog.add(new MachineUsage(oldTime, newEndTime, orderId, getMachineName(task, machine.getRank())));
        return newEndTime;
    }

    public List<MachineUsage> getLog() {
        return usageLog;
    }
}

@Data
@AllArgsConstructor
class Machine implements Comparable<Machine> {
    private int endtime;
    private int rank;

    @Override
    public int compareTo(Machine o) {
        return this.endtime - o.endtime;
    }
}