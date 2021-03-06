/*
 * This file is part of Minemod Launcher.
 *
 * Copyright © 2012-2013, Minemod Launcher Contributors <https://github.com/Minemod/Minemod-Launcher/>
 * Minemod Launcher is licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * This file is part of FTB Launcher.
 *
 * Copyright © 2012-2013, FTB Launcher Contributors <https://github.com/Slowpoke101/FTBLaunch/>
 * FTB Launcher is licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.ftb.gui.dialogs;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.ftb.gui.LaunchFrame;
import net.ftb.gui.panes.ModpacksPane;

@SuppressWarnings("serial")
public class SearchDialog extends JDialog {
	public static String lastPackSearch = "", lastMapSearch = "", lastTextureSearch = "";
	public JTextField query = new JTextField(20);

	public SearchDialog(final ModpacksPane instance) {
		super(LaunchFrame.getInstance(), true);
		setupGui();
		query.setText((lastPackSearch == null) ? "" : lastPackSearch);
		query.getDocument().addDocumentListener(new DocumentListener() {
			@Override public void removeUpdate(DocumentEvent arg0) {
				lastPackSearch = query.getText();
				instance.sortPacks();
			}
			@Override public void insertUpdate(DocumentEvent arg0) {
				lastPackSearch = query.getText();
				instance.sortPacks();
			}
			@Override public void changedUpdate(DocumentEvent arg0) { }
		});
		query.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				lastPackSearch = query.getText();
				instance.sortPacks();
				setVisible(false);
			}
		});
	}

	private void setupGui() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/image/logo_ftb.png")));
		setTitle("Text Search Filter");
		setResizable(false);

		Container panel = getContentPane();
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);

		panel.add(query);

		Spring vSpring;

		vSpring = Spring.constant(10);

		layout.putConstraint(SpringLayout.NORTH, query, vSpring, SpringLayout.NORTH, panel);

		vSpring = Spring.sum(vSpring, Spring.height(query));
		vSpring = Spring.sum(vSpring, Spring.constant(10));

		layout.putConstraint(SpringLayout.SOUTH, panel, vSpring, SpringLayout.NORTH, panel);

		Spring hSpring;

		hSpring = Spring.constant(10);

		layout.putConstraint(SpringLayout.WEST, query, hSpring, SpringLayout.WEST, panel);

		hSpring = Spring.sum(hSpring, Spring.width(query));
		hSpring = Spring.sum(hSpring, Spring.constant(10));

		layout.putConstraint(SpringLayout.EAST, panel, hSpring, SpringLayout.WEST, panel);

		pack();
		setLocationRelativeTo(getOwner());
	}
}
