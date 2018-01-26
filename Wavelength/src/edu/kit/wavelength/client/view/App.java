package edu.kit.wavelength.client.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.kit.wavelength.client.model.library.Libraries;
import edu.kit.wavelength.client.model.library.Library;
import edu.kit.wavelength.client.model.output.OutputSize;
import edu.kit.wavelength.client.model.output.OutputSizes;
import edu.kit.wavelength.client.model.reduction.ReductionOrder;
import edu.kit.wavelength.client.model.reduction.ReductionOrders;
import edu.kit.wavelength.client.model.serialization.Serializable;
import edu.kit.wavelength.client.view.action.RunNewExecution;
import edu.kit.wavelength.client.view.action.SelectExercise;
import edu.kit.wavelength.client.view.execution.Executor;
import edu.kit.wavelength.client.view.exercise.Exercise;
import edu.kit.wavelength.client.view.exercise.Exercises;
import edu.kit.wavelength.client.view.export.Exports;
import edu.kit.wavelength.client.view.update.UpdateTreeOutput;
import edu.kit.wavelength.client.view.update.UpdateUnicodeOutput;
import edu.kit.wavelength.client.view.webui.component.Checkbox;
import edu.kit.wavelength.client.view.webui.component.DisclosureButton;
import edu.kit.wavelength.client.view.webui.component.Editor;
import edu.kit.wavelength.client.view.webui.component.ImageButton;
import edu.kit.wavelength.client.view.webui.component.OptionBox;
import edu.kit.wavelength.client.view.webui.component.PopUpWindow;
import edu.kit.wavelength.client.view.webui.component.TextButton;
import edu.kit.wavelength.client.view.webui.component.TextField;
import edu.kit.wavelength.client.view.webui.component.TreeOutput;
import edu.kit.wavelength.client.view.webui.component.UnicodeOutput;
import edu.kit.wavelength.client.view.webui.gwtbinding.MonacoEditor;

/**
 * App is a singleton that initializes and holds the view.
 */
public class App implements Serializable {
	
	private static App instance = null;
	
	private App() {}
	
	/**
	 * Gets a singleton instance of App.
	 * @return instance
	 */
	public static App get() {
		if (instance == null) {
			instance = new App();
			instance.initialize();
		}
		return instance;
	}
	
	/**
	 * Name of the unicode format.
	 */
	public static final String UnicodeOutputName = "Unicode";
	/**
	 * Name of the tree format.
	 */
	public static final String TreeOutputName = "Tree";
	
	private DisclosureButton mainMenuButton;
	private Editor editor;
	private OptionBox outputFormat;
	private OptionBox reductionOrder;
	private OptionBox outputSize;
	private ImageButton stepBackward;
	private ImageButton stepByStepMode;
	private ImageButton stepForward;
	private ImageButton terminate;
	private ImageButton run;
	private ImageButton pause;
	private TreeOutput treeOutput;
	private UnicodeOutput unicodeOutput;
	private ImageButton export;
	private PopUpWindow exportWindow;
	private ImageButton share;
	private TextField sharePanel;
	private List<Checkbox> libraries = new ArrayList<Checkbox>();
	private List<TextButton> exercises = new ArrayList<TextButton>();
	private List<TextButton> exportFormats = new ArrayList<TextButton>();
	private Executor executor;
	
	private TextButton showSolution;
	private TextButton hideSolution;
	private TextButton exitExerciseModeButton;
	private PopUpWindow enterExerciseMode;
	private PopUpWindow leaveExerciseMode;
	private TextField solutionPanel;
	private TextField taskPanel;
	
	// etc.
	
	/**
	 * Gets the panel that contains the URL to play back the state of the application.
	 * @return The panel containing the share-URL
	 */
	public TextField sharePanel() {
		return this.sharePanel;
	}
	
	/**
	 * Gets the window that shows exported output.
	 * @return The export window
	 */
	public PopUpWindow exportWindow() {
		return this.exportWindow;
	}
	
	/**
	 * Gets the button that is used to open the main menu.
	 * @return The main menu button
	 */
	public DisclosureButton mainMenuButton() {
		return mainMenuButton;
	}
	
	/**
	 * Gets the editor.
	 * @return The editor
	 */
	public Editor editor() {
		return editor;
	}
	
	/**
	 * Gets the option box that allows the user to choose which output format to use.
	 * @return The output format box
	 */
	public OptionBox outputFormatBox() {
		return outputFormat;
	}
	
	/**
	 * Gets the option box that allows the user to choose which reduction order to use.
	 * @return The reduction order option box
	 */
	public OptionBox reductionOrderBox() {
		return reductionOrder;
	}
	
	/**
	 * Gets the option box that allows the user to choose which output size to use.
	 * @return The output size option box
	 */
	public OptionBox outputSizeBox() {
		return outputSize;
	}
	
	/**
	 * Gets the button that can be used to play back to the previous displayed term.
	 * @return The step backward button
	 */
	public ImageButton stepBackwardButton() {
		return stepBackward;
	}
	
	/**
	 * Gets the button that can be used to initiate step by step reduction before execution.
	 * @return The step-by-step button
	 */
	public ImageButton stepByStepModeButton() {
		return stepByStepMode;
	}
	
	/**
	 * Gets the button that can be used to initiate the next reduction by the currently selected reduction order.
	 * @return The step forward button
	 */
	public ImageButton stepForwardButton() {
		return stepForward;
	}
	
	/**
	 * Gets the button that can be used to terminate the reduction.
	 * @return The terminate button
	 */
	public ImageButton terminateButton() {
		return terminate;
	}
	
	/**
	 * Gets the button that can be used to initiate the execution, automatically reducing the input with the given options
	 * @return The run button
	 */
	public ImageButton runButton() {
		return run;
	}
	
	/**
	 * Gets the button that can be used to transition from the automatic execution to the step by step mode.
	 * @return The pause button
	 */
	public ImageButton pauseButton() {
		return pause;
	}
	
	/**
	 * Gets the output that displays terms as trees.
	 * @return The output used to display a term in tree representation
	 */
	public TreeOutput treeOutput() {
		return treeOutput;
	}
	
	/**
	 * Gets the output that displays terms with unicode text.
	 * @return The output used to display a term in unicode
	 */
	public UnicodeOutput unicodeOutput() {
		return unicodeOutput;
	}
	
	/**
	 * Gets the button that can be used to open the menu that allows the user to choose an export format.
	 * @return The export button
	 */
	public ImageButton exportButton() {
		return export;
	}
	
	/**
	 * Gets the button that can be used to toggle the panel that displays the serialized URL.
	 * @return The share button
	 */
	public ImageButton shareButton() {
		return share;
	}
	
	/**
	 * Gets all checkboxes that can be used to enable libraries.
	 * @return The checkboxes used to toggle libraries
	 */
	public List<Checkbox> libraryBoxes() {
		return libraries;
	}
	
	/**
	 * Gets all buttons that can be used to load an exercise.
	 * @return The buttons used to load exercises
	 */
	public List<TextButton> exerciseButtons() {
		return exercises;
	}
	
	/**
	 * Gets all buttons that can be used to load the output into the export window with the given export format specified by the button.
	 * @return The buttons used to select an output format
	 */
	public List<TextButton> exportFormatButtons() {
		return exportFormats;
	}
	
	/**
	 * Gets the wrapper that controls the reduction of lambda terms.
	 * @return The Executor instance controlling the execution
	 */
	public Executor executor() {
		return executor;
	}
	
	/**
	 * Gets the Button that can be used for showing the solution of the current {@link Exercise}.
	 * @return The Button used for showing the current {@link Exercise}'s solution
	 */
	public TextButton showSolutionButton() {
		return this.showSolution;
	}
	
	/**
	 * Gets the Button that can be used for hiding the solution of the current {@link Exercise}.
	 * @return The Button used for hiding the current {@link Exercise}'s solution
	 */
	public TextButton hideSolutionButton() {
		return this.hideSolution;
	}
	
	/**
	 * Gets the Button that can be used for exiting the exercise mode.
	 * @return The Button used for exiting the exercise mode
	 */
	public TextButton exitExerciseModeButton() {
		return this.exitExerciseModeButton;
	}
	
	/**
	 * Gets the Dialog that is shown when entering exercise mode.
	 * @return The Dialog shown when entering exercise mode
	 */
	public PopUpWindow enterExerciseMode() {
		return this.enterExerciseMode;
	}
	
	/**
	 * Gets the Dialog that is shown when leaving exercise mode.
	 * @return The Dialog shown when leaving exercise mode
	 */
	public PopUpWindow leaveExerciseMode() {
		return this.leaveExerciseMode;
	}
	
	/**
	 * Gets the TextArea that can be used to show the current {@link Exercise}'s solution.
	 * @return The TextArea showing the current {@link Exercise}'s solution
	 */
	public TextField solutionPanel() {
		return this.solutionPanel;
	}
	
	/**
	 * Gets the TextArea that can be used to show the current {@link Exercise}'s task.
	 * @return The TextArea showing the current {@link Exercise}'s task
	 */
	public TextField taskPanel() {
		return this.taskPanel;
	}
	
	// etc.
	
	/**
	 * Initializes App.
	 */
	private void initialize() {
		
		String state = Window.Location.getPath();
		// deserialize
		
		Panel mainPanel = new VerticalPanel();
		Panel headerPanel = new SimplePanel();
		Panel editorPanel = new SimplePanel();
		Panel controlPanel = new HorizontalPanel();
		Panel outputPanel = new SimplePanel();
		Panel footerPanel = new HorizontalPanel();
		
		headerPanel.addStyleName("headerStyle");
		editorPanel.addStyleName("editorStyle");
		controlPanel.addStyleName("controlStyle");
		outputPanel.addStyleName("outputStyle");
		footerPanel.addStyleName("footerStyle");
		
		
		// id needed because MonacoEditor adds to panel div by id
		editorPanel.getElement().setId("editor");
		
		DisclosurePanel mainMenu = new DisclosurePanel("");
		mainMenu.setAnimationEnabled(true);
		mainMenu.addStyleName("mainMenuStyle");
		
		mainMenu.setHeader(new Label()); // label is just used to hide the arrow of the DisclosurePanel
		mainMenu.getHeader().addStyleName("mainMenuButtonStyle");
	    
		Panel mainMenuPanel = new VerticalPanel();
		mainMenuPanel.addDomHandler(handler -> mainMenu.setOpen(false), MouseOutEvent.getType());
		mainMenuPanel.addStyleName("mainMenuPanelStyle");
		
		//add libraries
		if (Libraries.all() != null) {
			for (Library library : Libraries.all()) {
				CheckBox checkBox = new CheckBox(library.getName());
				mainMenuPanel.add(checkBox);
				libraries.add(new Checkbox(checkBox, library.getName()));
			}
		} else {
			Label label = new Label("no libraries available");
			label.addStyleName("noLibraryLabelStyle");
			mainMenuPanel.add(label);
		}
		//add exercises
		if (Exercises.all() != null) {
			for (Exercise exercise : Exercises.all()) {
				Button button = new Button(exercise.getName());
				button.addClickHandler(event -> new SelectExercise(exercise).run());
				button.setTitle(exercise.getTask());
				mainMenuPanel.add(button);
				exercises.add(new TextButton(button, exercise.getName()));
			}
		} else {
			Label label = new Label("no exercises available");
			label.addStyleName("noExerciseLabelStyle");
			mainMenuPanel.add(label);
		}
		
		mainMenu.setContent(mainMenuPanel);
		headerPanel.add(mainMenu);
		
		//create ListBox for outputFormats
		ListBox outputBox = new ListBox();
		outputBox.addItem("Unicode Output");
		outputBox.addItem("Tree Output");
		controlPanel.add(outputBox);
		outputFormat = new OptionBox(outputBox);
		
		//create ListBox for reductionOrders
		ListBox reductionBox = new ListBox();
		if (ReductionOrders.all() != null) {
			ReductionOrders.all().stream().map(ReductionOrder::getName).forEach(reductionBox::addItem);
		} else {
			reductionBox.addItem("no reduction order available");
		}
		controlPanel.add(reductionBox);
		reductionOrder = new OptionBox(reductionBox);
		
		//create ListBox for outputSizes
		ListBox outputSizeBox = new ListBox();
		if (OutputSizes.all() != null) {
			OutputSizes.all().stream().map(OutputSize::getName).forEach(outputSizeBox::addItem);
		} else {
			outputSizeBox.addItem("no output size available");
		}
		controlPanel.add(outputSizeBox);
		outputSize = new OptionBox(outputSizeBox);
		
		PushButton stepBackwardButton = new PushButton();
		stepBackwardButton.addStyleName("stepBackwardStyle");
		controlPanel.add(stepBackwardButton);
		
		PushButton stepByStepButton = new PushButton();
		stepByStepButton.addStyleName("stepByStepStyle");
		controlPanel.add(stepByStepButton);
		
		PushButton stepForwardButton = new PushButton();
		stepForwardButton.addStyleName("stepForwardStyle");
		controlPanel.add(stepForwardButton);
		
		PushButton terminateButton = new PushButton();
		terminateButton.addStyleName("terminateStyle");
		controlPanel.add(terminateButton);
		
		PushButton runButton = new PushButton();
		runButton.addStyleName("runStyle");
		controlPanel.add(runButton);
		
		PushButton pauseButton = new PushButton();
		pauseButton.addStyleName("pauseStyle");
		controlPanel.add(pauseButton);
		
		TextArea textArea = new TextArea();
		textArea.addStyleName("unicodeOutputStyle");
		outputPanel.add(textArea);
		
		PushButton exportButton = new PushButton();
		exportButton.addStyleName("exportStyle");
		footerPanel.add(exportButton);
		
		PushButton shareButton = new PushButton();
		shareButton.addStyleName("shareStyle");
		footerPanel.add(shareButton);
		
		
		mainPanel.add(headerPanel);
		mainPanel.add(editorPanel);
		mainPanel.add(controlPanel);
		mainPanel.add(outputPanel);
		mainPanel.add(footerPanel);
				
		// ui needs to be created BEFORE loading the editor for the ids to exist
		RootLayoutPanel.get().add(mainPanel);
		MonacoEditor me = MonacoEditor.load(editorPanel);
		
		
		
		stepBackward = new ImageButton(new PushButton(), new Image(), new Image());
		stepByStepMode = new ImageButton(new PushButton(), new Image(), new Image());
		stepForward = new ImageButton(new PushButton(), new Image(), new Image());
		terminate = new ImageButton(new PushButton(), new Image(), new Image());
		run = new ImageButton(new PushButton(), new Image(), new Image());
		pause = new ImageButton(new PushButton(), new Image(), new Image());
		export = new ImageButton(new PushButton(), new Image(), new Image());
		share = new ImageButton(new PushButton(), new Image(), new Image());
		exportFormats = Exports.all().stream().map(e -> new TextButton(new Button(), e.getName())).collect(Collectors.toList());
		executor = new Executor(Arrays.asList(new UpdateUnicodeOutput(), new UpdateTreeOutput()));
		run.setAction(new RunNewExecution());
	}

	@Override
	public String serialize() {
		return null;
	}
}
