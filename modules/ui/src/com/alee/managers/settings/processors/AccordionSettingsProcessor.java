/*
 * This file is part of WebLookAndFeel library.
 *
 * WebLookAndFeel library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * WebLookAndFeel library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.managers.settings.processors;

import com.alee.extended.panel.AccordionListener;
import com.alee.extended.panel.WebAccordion;
import com.alee.managers.settings.SettingsProcessor;
import com.alee.managers.settings.SettingsProcessorData;
import com.alee.utils.TextUtils;

import java.util.List;

/**
 * Custom SettingsProcessor for {@link com.alee.extended.panel.WebAccordion} component.
 *
 * @author Mikle Garin
 * @see <a href="https://github.com/mgarin/weblaf/wiki/How-to-use-SettingsManager">How to use SettingsManager</a>
 * @see com.alee.managers.settings.SettingsManager
 * @see com.alee.managers.settings.SettingsProcessor
 */

public class AccordionSettingsProcessor extends SettingsProcessor<WebAccordion, String> implements AccordionListener
{
    /**
     * todo 1. Change save scheme from indices to IDs
     * todo 2. Add IDs into accordion panes
     */

    /**
     * Constructs SettingsProcessor using the specified SettingsProcessorData.
     *
     * @param data SettingsProcessorData
     */
    public AccordionSettingsProcessor ( final SettingsProcessorData data )
    {
        super ( data );
    }

    @Override
    protected void doInit ( final WebAccordion accordion )
    {
        accordion.addAccordionListener ( this );
    }

    @Override
    protected void doDestroy ( final WebAccordion accordion )
    {
        accordion.removeAccordionListener ( this );
    }

    @Override
    public void selectionChanged ()
    {
        save ();
    }

    @Override
    protected void doLoad ( final WebAccordion accordion )
    {
        // Empty string identifies empty selection
        final String string = loadValue ();
        final List<Integer> indices = string == null || string.trim ().equals ( "" ) ? null : TextUtils.stringToIntList ( string, "," );
        accordion.setSelectedIndices ( indices );
    }

    @Override
    protected void doSave ( final WebAccordion accordion )
    {
        // For empty selection empty string is used to avoid having null as value as this will call for default value on load
        final String value = TextUtils.listToString ( accordion.getSelectedIndices (), "," );
        saveValue ( value != null ? value : "" );
    }
}