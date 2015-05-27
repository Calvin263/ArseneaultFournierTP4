package com.marc.arseneault.tp4;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.test.AndroidTestCase;

import java.util.Locale;


public class i18nTest extends AndroidTestCase {

    private void setLocale(String language, String country) {
        Locale locale = new Locale(language, country);
        // here we update locale for date formatters
        Locale.setDefault(locale);
        // here we update locale for app resources
        Resources res = getContext().getResources();
        Configuration config = res.getConfiguration();
        config.locale = locale;
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    public void testEnglishLocale() {
        setLocale("en", "EN");
        String cancelString = getContext().getString(R.string.action_changeTaxes);
        assertEquals("Change tax floor", cancelString);
    }

    public void testFrenchLocale() {
        setLocale("fr", "CA");
        String cancelString = getContext().getString(R.string.action_changeTaxes);
        assertEquals("Changer le seuil de taxes", cancelString);
    }

}
