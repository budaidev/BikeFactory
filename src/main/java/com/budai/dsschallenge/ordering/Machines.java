package com.budai.dsschallenge.ordering;

import com.budai.dsschallenge.dto.MachineUsage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Machines {

    private PriorityQueue<Machine> cutting;
    private PriorityQueue<Machine> bending;
    private PriorityQueue<Machine> welding;
    private PriorityQueue<Machine> testing;
    private PriorityQueue<Machine> painting;
    private PriorityQueue<Machine> packing;

    List<MachineUsage> usageLog;

    public Machines() {
        cutting = init(6);
        bending = init(2);
        welding = init(3);
        testing = init(1);
        painting = init(4);
        packing = init(3);

        usageLog = new ArrayList<>();
    }

    private PriorityQueue<Machine> init(int capacity) {
        PriorityQueue<Machine> pq = new PriorityQueue<>(capacity);
        for (int i = 0; i < capacity; i++) {
            pq.add(new Machine(0, i + 1));
        }
        return pq;
    }

    public int scheduleCutting(String orderId, int quantity, int joblength) {
        int capacity = cutting.size();
        int remaining = quantity;
        int batchSize = 0;
        int newEndtime = 0;
        if (quantity > capacity) {
            batchSize = quantity / capacity;
            remaining = quantity % capacity;
            for (int i = 0; i < capacity; i++) {
                newEndtime = updateMachineJob(joblength * batchSize);
            }

        }
        for (int i = 0; i < remaining; i++) {
            newEndtime = updateMachineJob(joblength);
        }
        return newEndtime;
    }

    private int updateMachineJob(int joblength) {
        Machine machine = cutting.poll();
        int newEndtime = machine.getEndtime() + joblength;
        cutting.add(new Machine(newEndtime, machine.getRank()));
        return newEndtime;
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