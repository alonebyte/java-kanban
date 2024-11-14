import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    // Генерация нового уникального ID
    

    // Методы добавления задач
     int addNewTask(Task task);

     int addNewEpic(Epic epic);

     Integer addNewSubtask(Subtask subtask);

    // Методы получения задач
     List<Task> getTasks();

     List<Epic> getEpics();

     List<Subtask> getSubtasks();

     List<Subtask> getEpicSubtasks(int epicId);

     Task getTask(int id);

     Epic getEpic(int id);

     Subtask getSubtask(int id);

    // Методы обновления задач
     void updateTask(Task task);

     void updateEpic(Epic epic);

     void updateSubtask(Subtask subtask);

    // Методы удаления задач
     void deleteTask(int id);

     void deleteEpic(int id);

     void deleteSubtask(int id);

     void deleteTasks();

     void deleteEpics();

     void deleteSubtasks();

    // Метод пересчёта статуса эпика
    void recalculateEpicStatus(int epicId);
}
