package students.frame;

import java.sql.SQLException;
import java.util.Vector;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import students.logic.Lan;
import students.logic.ManagementSystem;
import students.logic.IP;

public class IPsFrame extends JFrame implements ActionListener, ListSelectionListener, ChangeListener {
    // Введем сразу имена для кнопок - потом будем их использовать в обработчиках

    private static final String MOVE_GR = "moveLan";
    private static final String CLEAR_GR = "clearLan";
    private static final String INSERT_ST = "insertIP";
    private static final String UPDATE_ST = "updateIP";
    private static final String DELETE_ST = "deleteIP";
    private static final String ALL_IPS = "allIP";
    private ManagementSystem ms = null;
    private JList grpList;
    private JTable stdList;
    private JSpinner spYear;

    public IPsFrame() throws Exception {
        // Устанавливаем layout для всей клиентской части формы
        getContentPane().setLayout(new BorderLayout());

        // Создаем строку меню
        JMenuBar menuBar = new JMenuBar();
        // Создаем выпадающее меню
        JMenu menu = new JMenu("Отчеты");
        // Создаем пункт в выпадающем меню
        JMenuItem menuItem = new JMenuItem("Все Сети");
        menuItem.setName(ALL_IPS);
        // Добавляем листенер
        menuItem.addActionListener(this);
        // Вставляем пункт меню в выпадающее меню
        menu.add(menuItem);
        // Вставляем выпадающее меню в строку меню
        menuBar.add(menu);
        // Устанавливаем меню для формы
        setJMenuBar(menuBar);

        // Создаем верхнюю панель, где будет поле для ввода года
        JPanel top = new JPanel();
        // Устанавливаем для нее layout
        top.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Вставляем пояснительную надпись
        top.add(new JLabel("Год обучения:"));
        // Делаем спин-поле
        // 1. Задаем модель поведения - только цифры
        // 2. Вставляем в панель
        SpinnerModel sm = new SpinnerNumberModel(2006, 1900, 2100, 1);
        spYear = new JSpinner(sm);
        // Добавляем листенер
        spYear.addChangeListener(this);
        top.add(spYear);

        // Создаем нижнюю панель и задаем ей layout
        JPanel bot = new JPanel();
        bot.setLayout(new BorderLayout());

        // Создаем левую панель для вывода списка групп
        // Она у нас
        LanPanel left = new LanPanel();
        // Задаем layout и задаем "бордюр" вокруг панели
        left.setLayout(new BorderLayout());
        left.setBorder(new BevelBorder(BevelBorder.LOWERED));

        // Получаем коннект к базе и создаем объект ManagementSystem
        ms = ManagementSystem.getInstance();
        // Получаем список групп
        Vector<Lan> gr = new Vector<Lan>(ms.getLans());
        // Создаем надпись
        left.add(new JLabel("Подсети:"), BorderLayout.NORTH);
        // Создаем визуальный список и вставляем его в скроллируемую
        // панель, которую в свою очередь уже кладем на панель left
        grpList = new JList(gr);
        // Добавляем листенер
        grpList.addListSelectionListener(this);
        // Сразу выделяем первую группу
        grpList.setSelectedIndex(0);
        left.add(new JScrollPane(grpList), BorderLayout.CENTER);
        // Создаем кнопки для групп
        JButton btnMvGr = new JButton("Переместить");
        btnMvGr.setName(MOVE_GR);
        JButton btnClGr = new JButton("Очистить");
        btnClGr.setName(CLEAR_GR);
        // Добавляем листенер
        btnMvGr.addActionListener(this);
        btnClGr.addActionListener(this);
        // Создаем панель, на которую положим наши кнопки и кладем ее вниз
        JPanel pnlBtnGr = new JPanel();
        pnlBtnGr.setLayout(new GridLayout(1, 2));
        pnlBtnGr.add(btnMvGr);
        pnlBtnGr.add(btnClGr);
        left.add(pnlBtnGr, BorderLayout.SOUTH);

        // Создаем правую панель для вывода списка студентов
        JPanel right = new JPanel();
        // Задаем layout и задаем "бордюр" вокруг панели
        right.setLayout(new BorderLayout());
        right.setBorder(new BevelBorder(BevelBorder.LOWERED));

        // Создаем надпись
        right.add(new JLabel("Хосты:"), BorderLayout.NORTH);
        // Создаем таблицу и вставляем ее в скроллируемую
        // панель, которую в свою очередь уже кладем на панель right
        // Наша таблица пока ничего не умеет - просто положим ее как заготовку
        
        stdList = new JTable(1, 4);
        right.add(new JScrollPane(stdList), BorderLayout.CENTER);
        // Создаем кнопки для студентов
        JButton btnAddSt = new JButton("Добавить");
        btnAddSt.setName(INSERT_ST);
        btnAddSt.addActionListener(this);
        JButton btnUpdSt = new JButton("Fix");
        btnUpdSt.setName(UPDATE_ST);
        btnUpdSt.addActionListener(this);
        JButton btnDelSt = new JButton("Удалить");
        btnDelSt.setName(DELETE_ST);
        btnDelSt.addActionListener(this);
        // Создаем панель, на которую положим наши кнопки и кладем ее вниз
        JPanel pnlBtnSt = new JPanel();
        pnlBtnSt.setLayout(new GridLayout(1, 3));
        pnlBtnSt.add(btnAddSt);
        pnlBtnSt.add(btnUpdSt);
        pnlBtnSt.add(btnDelSt);
        right.add(pnlBtnSt, BorderLayout.SOUTH);

        // Вставляем панели со списками групп и студентов в нижнюю панель
        bot.add(left, BorderLayout.WEST);
        bot.add(right, BorderLayout.CENTER);

        // Вставляем верхнюю и нижнюю панели в форму
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(bot, BorderLayout.CENTER);

        // Задаем границы формы
        setBounds(100, 100, 700, 500);
    }

    // Метод для обеспечения интерфейса ActionListener
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Component) {
            Component c = (Component) e.getSource();
            if (c.getName().equals(MOVE_GR)) {
                moveLan();
            }
            if (c.getName().equals(CLEAR_GR)) {
                clearLan();
            }
            if (c.getName().equals(ALL_IPS)) {
                showAllIPs();
            }
            if (c.getName().equals(INSERT_ST)) {
                insertIP();
            }
            if (c.getName().equals(UPDATE_ST)) {
                updateIP();
            }
            if (c.getName().equals(DELETE_ST)) {
                deleteIP();
            }
        }
    }

    // Метод для обеспечения интерфейса ListSelectionListener
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            reloadIPs();
        }
    }

    // Метод для обеспечения интерфейса ChangeListener
    public void stateChanged(ChangeEvent e) {
        reloadIPs();
    }

    // метод для обновления списка студентов для определенной группы
    private void reloadIPs() {
        // Создаем анонимный класс для потока
        Thread t = new Thread() {
            // Переопределяем в нем метод run
            public void run() {
                if (stdList != null) {
                    // Получаем выделенную группу
                    Lan g = (Lan) grpList.getSelectedValue();
                    // Получаем число из спинера
                    int y = ((SpinnerNumberModel) spYear.getModel()).getNumber().intValue();
                    try {
                        // Получаем список студентов
                        Collection<IP> s = ms.getIPsFromLan(g, y);
                        
                        stdList.setModel(new IPTableModel(new Vector<IP>(s)));
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(IPsFrame.this, e.getMessage());
                    }
                }
            }
            // Окончание нашего метода run
        };
        // Окончание определения анонимного класса

        
        t.start();
    }

    // метод для переноса группы
    private void moveLan() {
        JOptionPane.showMessageDialog(this, "moveLan");
    }

    // метод для очистки группы
    private void clearLan() {
        Thread t = new Thread() {
            public void run() {
                // Проверяем - выделена ли группа
                if (grpList.getSelectedValue() != null) {
                    if (JOptionPane.showConfirmDialog(IPsFrame.this,
                            "Вы хотите удалить студентов из группы?", "Удаление студентов",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        // Получаем выделенную группу
                        Lan g = (Lan) grpList.getSelectedValue();
                        // Получаем число из спинера
                        int y = ((SpinnerNumberModel) spYear.getModel()).getNumber().intValue();
                        try {
                            // Удалили студентов из группы
                            ms.removeIPsFromLan(g, y);
                            // перегрузили список студентов
                            reloadIPs();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(IPsFrame.this, e.getMessage());
                        }
                    }
                }
            }
        };
        t.start();
    }

    // метод для добавления студента
    private void insertIP() {
        JOptionPane.showMessageDialog(this, "insertIP");
    }

    // метод для редактирования студента
    private void updateIP() {
        JOptionPane.showMessageDialog(this, "updateIP");
    }

    // метод для удаления студента
    private void deleteIP() {
        Thread t = new Thread() {
            public void run() {
                if (stdList != null) {
                    IPTableModel stm = (IPTableModel) stdList.getModel();
                    // Проверяем - выделен ли хоть какой-нибудь студент
                    if (stdList.getSelectedRow() >= 0) {
                        if (JOptionPane.showConfirmDialog(IPsFrame.this,
                                "Вы хотите удалить студента?", "Удаление студента",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            // Вот где нам пригодился метод getIP(int rowIndex)
                            IP s = stm.getIP(stdList.getSelectedRow());
                            try {
                                ms.deleteIP(s);
                                reloadIPs();
                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(IPsFrame.this, e.getMessage());
                            }
                        }
                    } // Если студент не выделен - сообщаем пользователю, что это необходимо
                    else {
                        JOptionPane.showMessageDialog(IPsFrame.this, "Необходимо выделить студента в списке");
                    }
                }
            }
        };
        t.start();
    }

    // метод для показа всех студентов
    private void showAllIPs() {
        JOptionPane.showMessageDialog(this, "showAllIPs");
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    // Мы сразу отменим продолжение работы, если не сможем получить
                    // коннект к базе данных
                    IPsFrame sf = new IPsFrame();
                    sf.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    sf.setVisible(true);
                    sf.reloadIPs();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
    }
}

// Наш внутренний класс - переопределенная панель.
class LanPanel extends JPanel {

    public Dimension getPreferredSize() {
        return new Dimension(250, 0);
    }
}