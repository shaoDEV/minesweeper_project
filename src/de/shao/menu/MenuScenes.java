package de.shao.menu;

import de.shao.driver.SystemResources;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class MenuScenes {

    //Credits:
    //Die gesamte Idee mit verschiednene Szenen habe ich bereits angefangen mithilfe von sich überlabenden Panel implementiert.
    //Durch den gemeinsamen Austausch mit den anderen Azubis, insbesondere Sascha SChmeichel, hat sich herausgestellt das mithilfe von Szenen zu arbeiten deutlich einfach und besser ist.
    //Es wurde kein Code kopoert lediglcih die Idee auf einen eigenen Weise umgesetzt.

    SystemResources systemResources = null;
    boolean isSceneActive = true;

    /**
     * Zeichnet alle Objecte der Szene an ihre feste Position im Panel, die teilweise erst berehchnet werden müssen.
     * @param graphics2D Graphics Object
     * @return Gibt zurück ob die Scene weiterhin aktiv ist oder ob eine neue Szene in die queue gelegt werden muss
     */
    abstract boolean drawScene(Graphics2D graphics2D);

    /**
     * Verarbeitet das MouseEevent aus dem MenuPanel und führt die Interaktionen der einzelnen gezeichneten Componentne aus,
     * @param mouseEvent Mouseevent
     */
    abstract void mouseInteraction(MouseEvent mouseEvent);

    /**
     * Übergibt welche Taste gedrückt wurde damit die Componenten damit arbeiten können.
     * @param keyEvent KeyEvent
     */
    abstract void keyInteraction(KeyEvent keyEvent);

    /**
     * Gibt an den Aufrufer zurück welche Szene als nächsten in die queue eingereiht wird. Wird meisten am Ende einer Scene abgespielt.
     * @return
     */
    abstract MenuScenes getFollowUpScene();
}
