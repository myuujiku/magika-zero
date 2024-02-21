package myujiku.magikazero.gui;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.OwoUIAdapter;
import io.wispforest.owo.ui.core.Surface;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class BookOfKnowledgeUI extends BaseOwoScreen<FlowLayout> {
    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent.surface(Surface.VANILLA_TRANSLUCENT);

        rootComponent.child(
                Components.button(
                        Text.literal("lmao"),
                        button -> {
                            this.close();
                        }
                )
        );
    }
}
