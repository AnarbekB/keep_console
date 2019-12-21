package ru.anarbek.view;

import ru.anarbek.cli.Option;
import ru.anarbek.entity.Keep;
import ru.anarbek.provider.ProviderException;

import java.util.List;

public class ListKeep extends AbstractView implements View {

    ListKeep() throws ProviderException {
        super();
    }

    public void render(Option option) {
        try {
            List<Keep> keeps = dataProvider.getAll();

            if (keeps == null) {
                this.outputLn("Empty list");
                return;
            }

            StringBuilder stringBuilder = new StringBuilder("Keep list: \n");
            for (Keep keep : keeps) {
                stringBuilder.append(keep.getId());
                stringBuilder.append(" | ");
                stringBuilder.append(keep.getTitle());
                stringBuilder.append(" | ");
                stringBuilder.append(keep.getData());
                stringBuilder.append('\n');
            }

            this.outputLn(stringBuilder.toString());

        } catch (ProviderException e) {
            this.outputLn(e.getMessage());
        }
    }
}
