package com.appCent.toDoApp;

import com.appCent.toDoApp.model.ToDoTask;
import com.appCent.toDoApp.model.User;
import com.appCent.toDoApp.model.UserDTO;
import com.appCent.toDoApp.repository.UserRepository;
import com.appCent.toDoApp.services.TaskService;
import com.appCent.toDoApp.services.UserService;
import com.appCent.toDoApp.util.Priority;
import com.appCent.toDoApp.util.Status;
import lombok.NonNull;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ToDoAppApplicationTests {

	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;

	@MockBean
	private UserRepository repository;



	@Test
	public void loginTest(){

		User mockUser = new User();
		mockUser.setPassword("test");
		mockUser.setId("testId");

		Mockito.when(repository.findByName("test")).thenReturn(mockUser);

		Assert.assertEquals("testId",userService.login("test","test"));
	}

	@Test
	public void registerTest(){

		User mockUser = new User();
		mockUser.setName("testName");
		mockUser.setPassword("testPassword");
		mockUser.setEmail("testEmail");

		userService.register(mockUser);
		Mockito.verify(repository).save(mockUser);
	}

	@Test
	public void infoTest(){

		User mockUser = new User();
		mockUser.setId("testId");
		mockUser.setName("testName");
		mockUser.setPassword("testPassword");
		mockUser.setEmail("testEmail");

		Optional<User> mockOptional = Optional.of(mockUser);

		Mockito.when(repository.findById(mockUser.getId())).thenReturn(mockOptional);
		Assert.assertEquals(userService.getUserInfo(mockUser.getId()),new UserDTO(mockUser.getName(),mockUser.getEmail()));

	}

	@Test
	public void addTaskTest(){
		User mockUser = new User();
		mockUser.setId("testId");
		mockUser.setTasks(new ArrayList<ToDoTask>());

		Optional<User> mockOptional = Optional.of(mockUser);

		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		ToDoTask mockTask = new ToDoTask(0,"testDesc",Status.NOT_STARTED,Priority.LOW,dt);

		Mockito.when(repository.findById("testId")).thenReturn(mockOptional);
		Assert.assertEquals(mockTask,taskService.addTask("testId",mockTask).get(0));
	}

	@Test
	public void getTasksTest(){
		User mockUser = new User();
		mockUser.setId("testId");

		List<ToDoTask> mockTaskList = new ArrayList<ToDoTask>();
		mockTaskList.add(new ToDoTask(0,"testDesc",Status.NOT_STARTED,Priority.LOW,new Date()));
		mockTaskList.add(new ToDoTask(1,"testDesc2",Status.STARTED,Priority.MEDIUM,new Date()));
		mockTaskList.add(new ToDoTask(2,"testDesc2",Status.STARTED,Priority.MEDIUM,new Date()));

		mockUser.setTasks(mockTaskList);

		Optional<User> mockOptional = Optional.of(mockUser);

		Mockito.when(repository.findById("testId")).thenReturn(mockOptional);
		Assert.assertEquals(mockTaskList,taskService.getTasks("testId"));

	}

	@Test
	public void  getTasksByDate(){
		User mockUser = new User();
		mockUser.setId("testId");

		Date dt = new Date();

		List<ToDoTask> mockTaskListExpected = new ArrayList<ToDoTask>();
		mockTaskListExpected.add(new ToDoTask(0,"testDesc",Status.NOT_STARTED,Priority.LOW,dt));
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		mockTaskListExpected.add(new ToDoTask(1,"testDesc2",Status.STARTED,Priority.MEDIUM,dt));
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		mockTaskListExpected.add(new ToDoTask(2,"testDesc2",Status.STARTED,Priority.MEDIUM,null));

		List<ToDoTask> mockTaskList = new ArrayList<ToDoTask>(mockTaskListExpected);

		Collections.swap(mockTaskList,0,1);

		mockUser.setTasks(mockTaskList);

		Optional<User> mockOptional = Optional.of(mockUser);

		Mockito.when(repository.findById("testId")).thenReturn(mockOptional);
		Assert.assertEquals(mockTaskListExpected, taskService.getTasksByDate("testId"));
	}

	@Test
	public void  getTasksByPriority(){
		User mockUser = new User();
		mockUser.setId("testId");

		List<ToDoTask> mockTaskListExpected = new ArrayList<ToDoTask>();

		mockTaskListExpected.add(new ToDoTask(0,"testDesc",Status.NOT_STARTED,Priority.HIGH,null));
		mockTaskListExpected.add(new ToDoTask(1,"testDesc2",Status.STARTED,Priority.MEDIUM,null));
		mockTaskListExpected.add(new ToDoTask(2,"testDesc2",Status.STARTED,Priority.LOW,null));


		List<ToDoTask> mockTaskList = new ArrayList<ToDoTask>(mockTaskListExpected);

		Collections.swap(mockTaskList,0,1);

		mockUser.setTasks(mockTaskList);

		Optional<User> mockOptional = Optional.of(mockUser);

		Mockito.when(repository.findById("testId")).thenReturn(mockOptional);
		Assert.assertEquals(mockTaskListExpected, taskService.getTasksByPriority("testId"));
	}




}
