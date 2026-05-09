import DatePicker from "react-datepicker";
import { FaRegCalendarAlt } from "react-icons/fa";
import "react-datepicker/dist/react-datepicker.css";

const MyDatePicker = ({ onChange, value, placeholder, minDate, disabled, setDate, theme }) => {


  return (
    <div className="relative w-full">
      <DatePicker
        selected={value || null}
        onChange={onChange ? onChange : (newDate) => setDate(newDate)}
        minDate={minDate}
        disabled={disabled}
        dateFormat="yyyy-MM-dd"
        className={`w-full p-2 pl-10 border border-gray-400 rounded-xl focus:outline-none ${theme}`}
        placeholderText={placeholder || "Select a date"}
        wrapperClassName="w-full"
      />
      <FaRegCalendarAlt className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 pointer-events-none" />
    </div>
  );
};

export default MyDatePicker;