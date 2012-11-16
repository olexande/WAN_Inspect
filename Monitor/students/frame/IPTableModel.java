package students.frame;

import java.text.DateFormat;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import students.logic.IP;

public class IPTableModel extends AbstractTableModel {
    // Сделаем хранилище для нашего списка студентов

    private Vector ips;

    // Модель при создании получает список студентов
    public IPTableModel(Vector ips) {
        this.ips = ips;
    }

    // Количество строк равно числу записей
    public int getRowCount() {
        if (ips != null) {
            return ips.size();
        }
        return 0;
    }

    
    public int getColumnCount() {
        return 4;
    }

    // Вернем наименование колонки
    public String getColumnName(int column) {
        String[] colNames = {"IP-adress", "Пункт", "MAC-address", "Date"};
        return colNames[column];
    }

    // Возвращаем данные для определенной строки и столбца
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (ips != null) {
            // Получаем из вектора студента
            IP st = (IP) ips.get(rowIndex);
            // В зависимости от колонки возвращаем имя, фамилия и т.д.
            switch (columnIndex) {
                case 0:
                    return st.getSurName();
                case 1:
                    return st.getFirstName();
                case 2:
                    return st.getPatronymic();
                case 3:
                    return DateFormat.getDateInstance(DateFormat.SHORT).format(
                            st.getDateOfBirth());
            }
        }
        return null;
    }

    // Добавим метод, который возвращает студента по номеру строки
    // Это нам пригодится чуть позже
    public IP getIP(int rowIndex) {
        if (ips != null) {
            if (rowIndex < ips.size() && rowIndex >= 0) {
                return (IP) ips.get(rowIndex);
            }
        }
        return null;
    }
}