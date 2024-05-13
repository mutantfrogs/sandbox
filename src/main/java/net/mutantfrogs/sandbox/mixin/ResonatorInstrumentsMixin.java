package net.mutantfrogs.sandbox.mixin;

import net.minecraft.item.Instrument;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Arrays;

@Mixin(Instrument.class)
public class ResonatorInstrumentsMixin {
    public interface InstrumentAccessor {
        @Accessor("VALUES")
        static Instrument[] getValues() {
            throw new AssertionError();
        }

        static Instrument[] addInstrumentToValues(Instrument newInstrument) {
            Instrument[] values = getValues();
            Instrument[] newValues = Arrays.copyOf(values, values.length + 1);
            newValues[values.length] = newInstrument;
            return newValues;
        }
    }
}
