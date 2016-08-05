package algorithms.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Build a car given all tasks and each task's dependencies.
 *
 * Set up data structure (Task) that stores the task AND its dependencies (other tasks).
 *
 * The recursion method sould take in a list of all tasks that are needed to build a car, along with their dependencies,
 * and execute each task in order as we build the car.
 *
 * Tasks can be given in any order; it's up to the algorithm to determine some order in which it can be executed.
 *
 * Base case:
 * 1. the current task has been executed, and it's marked DONE
 *
 * Recursive case:
 * 1. execute dependencies before coming to the current task
 *
 * Created by cindymc on 8/5/16.
 */
public class BuildCar
{
    public static void main(String [] args)
    {
        // Setup
        Task A = new Task("A");
        Task B = new Task("B");
        Task C = new Task("C");
        Task D = new Task("D");
        Task E = new Task("E");
        Task F = new Task("F");

        B.addDependency(A);
        D.addDependency(E);
        C.addDependency(A);
        C.addDependency(B);
        C.addDependency(D);
        F.addDependency(C);

//        for (Task task : taskList)
        for (Task task : Arrays.asList( new Task[]{A,B,C,D,E,F}))
        {
            task.execute();
        }
    }

    static class Task
    {
        private String id;
        private List<Task> dependencies;
        private boolean done = false;

        public Task(String id)
        {
            this.id = id;
            dependencies = new ArrayList<>();
        }
        public void addDependency(Task task)
        {
            dependencies.add(task);
        }

        // Here's the recursive method
        public void execute()
        {
            if (done) return;

            for (Task task : dependencies)
            {
                task.execute();
            }

            // Bottom of stack
            runTask();
        }

        private void runTask()
        {
            done = true;
            System.err.println("Completed task " + id);
        }
    }
}
