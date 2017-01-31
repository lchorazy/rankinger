package chorazy;

import chorazy.model.Country;
import chorazy.model.MatchResult;
import chorazy.model.Season;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static com.vaadin.server.Sizeable.Unit.PERCENTAGE;

@SpringUI
@Theme("valo")
public class Start extends UI {

    @Autowired
    private
    Simulation simulation;

    private Table mainTable = new Table();

    @Override
    protected void init(VaadinRequest request) {
        GridLayout layout = new GridLayout(2, 2);

        Panel tablePanel = new Panel("Results over 5 seasons");
        tablePanel.setWidth("1000px");
        tablePanel.setHeight("700px");
        layout.addComponent(tablePanel, 0, 0);

        VerticalLayout tableLayout = new VerticalLayout();
        tableLayout.setSizeFull();
        tableLayout.setMargin(true);
        tablePanel.setContent(tableLayout);

        mainTable.setContainerDataSource(
                new BeanItemContainer<>(Country.class, simulation.run()));
        mainTable.setVisibleColumns("name", "potential", "totalScore");
        mainTable.setHeight(100, PERCENTAGE);
        mainTable.setWidth(100, PERCENTAGE);
        mainTable.sort(new String[]{"score"}, new boolean[]{false});
        mainTable.setSelectable(true);
        tableLayout.addComponent(mainTable);


        Panel detailsPanel = new Panel("Country details");
        detailsPanel.setWidth("1000px");
        detailsPanel.setHeight("350px");
        detailsPanel.setVisible(false);
        layout.addComponent(detailsPanel, 0, 1);

        final HorizontalLayout detailsLayout = new HorizontalLayout();
        detailsLayout.setSizeFull();
        detailsLayout.setMargin(true);
        detailsPanel.setContent(detailsLayout);

        Panel seasonPanel = new Panel("Season details");
        seasonPanel.setWidth("1000px");
        seasonPanel.setHeight("800px");
        seasonPanel.setVisible(false);
        layout.addComponent(seasonPanel, 1, 0);

        final HorizontalLayout seasonLayout = new HorizontalLayout();
        seasonLayout.setSizeFull();
        seasonLayout.setMargin(true);
        seasonPanel.setContent(seasonLayout);

        mainTable.addValueChangeListener((Property.ValueChangeListener) event -> {
            Country selectedCountry = (Country) event.getProperty().getValue();
            if (selectedCountry == null) {
                detailsPanel.setVisible(false);
            } else {
                detailsLayout.removeAllComponents();
                Table detailsTable = new Table();
                detailsTable.setHeight(100, PERCENTAGE);
                detailsTable.setWidth(100, PERCENTAGE);
                detailsTable.setContainerDataSource(
                        new BeanItemContainer<>(Season.class, selectedCountry.getLast5Seasons()));
                detailsTable.setVisibleColumns("wins", "draws", "losses", "score");
                detailsLayout.addComponent(detailsTable);
                detailsPanel.setVisible(true);

                detailsTable.addValueChangeListener((Property.ValueChangeListener) seasonEvent -> {
                    Season selectedSeason = (Season) seasonEvent.getProperty().getValue();
                    if (selectedSeason == null) {
                        seasonPanel.setVisible(false);
                    } else {
                        seasonLayout.removeAllComponents();
                        Table seasonTable = new Table();
                        seasonTable.setHeight(100, PERCENTAGE);
                        seasonTable.setWidth(100, PERCENTAGE);
                        List<MatchResult> newList = new ArrayList<MatchResult>() {{
                            addAll(selectedSeason.getWinningResults());
                            addAll(selectedSeason.getDrawingResults());
                            addAll(selectedSeason.getLosingResults());
                        }};
                        seasonTable.setContainerDataSource(
                                new BeanItemContainer<>(MatchResult.class, newList));
                        seasonTable.setVisibleColumns("countryHome", "countryAway", "winner", "homeGoals", "awayGoals");
                        seasonLayout.addComponent(seasonTable);
                        seasonPanel.setVisible(true);
                    }
                });
            }
        });
        setContent(layout);
    }
}