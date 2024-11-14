public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager manager = new InMemoryTaskManager();

        // Создание двух задач
        Task task1 = new Task("Задача 1", "Создать приложение", TaskStatus.NEW);
        Task task2 = new Task("Задача 2", "Сдать приложение", TaskStatus.NEW);
        manager.addNewTask(task1);
        manager.addNewTask(task2);

        // Создание эпика с двумя подзадачами
        Epic epic1 = new Epic("Эпик 1", "Проснуться утром");
        manager.addNewEpic(epic1);

        Subtask subtask1 = new Subtask("Подзадача 1", "Открыть глаза", TaskStatus.NEW, epic1.getId());
        Subtask subtask2 = new Subtask("Подзадача 1", "Встатьс с кровати", TaskStatus.NEW, epic1.getId());
        manager.addNewSubtask(subtask1);
        manager.addNewSubtask(subtask2);

        // Создание эпика с одной подзадачей
        Epic epic2 = new Epic("Эпик 3", "Начать работать");
        manager.addNewEpic(epic2);

        Subtask subtask3 = new Subtask("Подзача 3", "Выключить соцсести", TaskStatus.NEW, epic2.getId());
        manager.addNewSubtask(subtask3);

        // Вывод списков задач
        System.out.println("Задачи: " + manager.getTasks());
        System.out.println("Эпики: " + manager.getEpics());
        System.out.println("Подзадачи: " + manager.getSubtasks());

        // Изменение статусов
        subtask1.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subtask1);

        subtask2.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subtask2);

        subtask3.setStatus(TaskStatus.IN_PROGRESS);
        manager.updateSubtask(subtask3);

        System.out.println("\n Обновленные задачи:");
        System.out.println("Эпики: " + manager.getEpics());

        // Удаление задачи и эпика
        manager.deleteTask(task1.getId());
        manager.deleteEpic(epic1.getId());

        System.out.println("\nСписок задач после удаления :");
        System.out.println("Задачи: "+ manager.getTasks());
        System.out.println("Эпики: " + manager.getEpics());
        System.out.println("Подзадачи: " + manager.getSubtasks());
    }
}
