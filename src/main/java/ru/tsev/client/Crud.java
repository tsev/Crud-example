package ru.tsev.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Button;
import ru.tsev.shared.dto.UserDTO;
import ru.tsev.shared.services.UserServiceAsync;

import java.util.List;

public class Crud implements EntryPoint {

    private final UserServiceAsync userService = UserServiceAsync.Util.getInstance();

    private Button createButton;
    private Button retrieveButton;
    private Button updateButton;
    private Button deleteButton;
    private CellTable<UserDTO> userTable;
    private DialogBox dBox;

    public void onModuleLoad() {
        dBox = new DialogBox();

        HorizontalPanel horizontalButtonPanel = new HorizontalPanel();
        horizontalButtonPanel.setSpacing(3);

        createButton = new Button("C");
        createButton.setTitle("Создать пользователя");
        createButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {

                dBox.setTitle("Add user");

                VerticalPanel dBoxContent = new VerticalPanel();
                dBox.add(dBoxContent);

                Grid grid = new Grid(2,2);

                grid.setWidget(0,0, new Label("Name"));
                final TextBox nameInput = new TextBox();
                grid.setWidget(0,1, nameInput);

                grid.setWidget(1,0, new Label("Age"));
                final TextBox ageInput = new TextBox();
                grid.setWidget(1,1, ageInput);

                HorizontalPanel buttonHorizontalPanel = new HorizontalPanel();
                buttonHorizontalPanel.setSpacing(3);

                Button createDboxButton = new Button("Create");
                createDboxButton.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {
                        UserDTO userDTO = new UserDTO(nameInput.getValue(), Integer.parseInt(ageInput.getValue()));
                        userService.create(userDTO, new AsyncCallback<Void>() {
                            @Override
                            public void onFailure(Throwable caught) {
                                Window.alert(caught.getMessage());
                            }

                            @Override
                            public void onSuccess(Void aVoid) {
                                dBox.hide();
                                dBox.clear();
                                Window.alert("Пользователь с именем: " + nameInput.getValue() + " создан");
                            }
                        });
                    }
                });

                Button closeDboxButton = new Button("Close");
                closeDboxButton.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {
                        dBox.hide();
                        dBox.clear();
                    }
                });

                buttonHorizontalPanel.add(createDboxButton);
                buttonHorizontalPanel.add(closeDboxButton);

                dBoxContent.add(grid);
                dBoxContent.add(buttonHorizontalPanel);
                dBox.center();
            }
        });

        retrieveButton = new Button("R");
        retrieveButton.setTitle("Обносить список пользователей");
        retrieveButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                userService.retrieveAll(new AsyncCallback<List<UserDTO>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(List<UserDTO> result) {
                        userTable.setRowData(result);
                        Window.alert("Список пользователей обновлён");
                    }
                });
            }
        });

        updateButton = new Button("U");
        updateButton.setTitle("Обновить пользователя");
        updateButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                dBox.setTitle("Update user");

                VerticalPanel dBoxContent = new VerticalPanel();
                dBox.add(dBoxContent);

                Grid grid = new Grid(3,2);

                grid.setWidget(0,0, new Label("id"));
                final TextBox idInput = new TextBox();
                grid.setWidget(0,1, idInput);

                grid.setWidget(1,0, new Label("Name"));
                final TextBox nameInput = new TextBox();
                grid.setWidget(1,1, nameInput);

                grid.setWidget(2,0, new Label("Age"));
                final TextBox ageInput = new TextBox();
                grid.setWidget(2,1, ageInput);

                HorizontalPanel buttonHorizontalPanel = new HorizontalPanel();
                buttonHorizontalPanel.setSpacing(3);

                Button updateDboxButton = new Button("Update");
                updateDboxButton.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {

                        Long userId = Long.parseLong(idInput.getValue());
                        boolean isUserExists = false;

                        for(UserDTO user : userTable.getVisibleItems()) {
                            if(user.getId() == userId) {
                                isUserExists = true;
                            }
                        }

                        if(isUserExists) {
                            userService.update(new UserDTO(userId, nameInput.getValue(), Integer.parseInt(ageInput.getValue())), new AsyncCallback<UserDTO>() {
                                @Override
                                public void onFailure(Throwable caught) {
                                    Window.alert("Ошибка при обновлении пользователя: " + caught.getMessage());
                                }

                                @Override
                                public void onSuccess(UserDTO result) {
                                    Window.alert("Пользователь с идентификатором:" + idInput.getValue() + " обновлен");
                                    dBox.hide();
                                    dBox.clear();
                                }
                            });
                        } else {
                            Window.alert("Нет пользователя с таким id: " + userId);
                        }
                    }
                });

                Button closeDboxButton = new Button("Close");
                closeDboxButton.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {
                        dBox.hide();
                        dBox.clear();
                    }
                });

                buttonHorizontalPanel.add(updateDboxButton);
                buttonHorizontalPanel.add(closeDboxButton);

                dBoxContent.add(grid);
                dBoxContent.add(buttonHorizontalPanel);
                dBox.center();
            }
        });

        deleteButton = new Button("D");
        deleteButton.setTitle("Удалить пользователя");
        deleteButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                dBox.setTitle("Delete user");

                VerticalPanel dBoxContent = new VerticalPanel();
                dBox.add(dBoxContent);

                Grid grid = new Grid(1,2);

                grid.setWidget(0,0, new Label("id"));
                final TextBox idInput = new TextBox();
                grid.setWidget(0,1, idInput);

                HorizontalPanel buttonHorizontalPanel = new HorizontalPanel();
                buttonHorizontalPanel.setSpacing(3);

                Button deleteDboxButton = new Button("Delete");
                deleteDboxButton.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {

                        userService.delete(Long.parseLong(idInput.getValue()), new AsyncCallback<Void>() {
                            @Override
                            public void onFailure(Throwable caught) {
                                Window.alert("Ошибка при удалении пользователя: " + caught.getMessage());
                            }

                            @Override
                            public void onSuccess(Void aVoid) {
                                Window.alert("Пользователь с идентификатором:" + idInput.getValue() + " удален");
                                dBox.hide();
                                dBox.clear();
                            }
                        });
                    }
                });

                Button closeDboxButton = new Button("Close");
                closeDboxButton.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {
                        dBox.hide();
                        dBox.clear();
                    }
                });

                buttonHorizontalPanel.add(deleteDboxButton);
                buttonHorizontalPanel.add(closeDboxButton);

                dBoxContent.add(grid);
                dBoxContent.add(buttonHorizontalPanel);
                dBox.center();

            }
        });

        horizontalButtonPanel.add(createButton);
        horizontalButtonPanel.add(retrieveButton);
        horizontalButtonPanel.add(updateButton);
        horizontalButtonPanel.add(deleteButton);

        userTable = new CellTable<UserDTO>();

        TextColumn<UserDTO> idColumn = new TextColumn<UserDTO>() {
            @Override
            public String getValue(UserDTO object) {
                return String.valueOf(object.getId());
            }
        };
        userTable.addColumn(idColumn, "id");

        TextColumn<UserDTO> nameColumn = new TextColumn<UserDTO>() {
            @Override
            public String getValue(UserDTO object) {
                return object.getName();
            }
        };
        userTable.addColumn(nameColumn, "Имя");

        TextColumn<UserDTO> ageColumn = new TextColumn<UserDTO>() {
            @Override
            public String getValue(UserDTO object) {
                return String.valueOf(object.getAge());
            }
        };
        userTable.addColumn(ageColumn, "Возраст");

        userService.retrieveAll(new AsyncCallback<List<UserDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }

            @Override
            public void onSuccess(List<UserDTO> result) {
                userTable.setRowData(result);
            }
        });

        RootPanel.get("toolBar").add(horizontalButtonPanel);
        RootPanel.get("content").add(userTable);
    }
}
