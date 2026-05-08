import { useState } from "react";
import Dropzone from "react-dropzone";

const MyPdfDropzone = () => {
  
  const [selectedFile, setSelectedFile] = useState(null);

  const onDrop = (acceptedFiles) => {
    if (acceptedFiles.length > 0) {
      setSelectedFile(acceptedFiles[0]);
      console.log("Selected PDF:", acceptedFiles[0]);
    }
  };

  return (
    <Dropzone
      onDrop={onDrop}
      accept={{ "application/pdf": [".pdf"] }}
      multiple={false}
    >
      {({ getRootProps, getInputProps, isDragActive }) => (
        <section className="w-full">
          <div
            {...getRootProps()}
            className={`w-full h-40 p-6 border-2 border-dashed rounded-xl
              flex flex-col items-center justify-center gap-3
              cursor-pointer transition-all duration-200
              bg-slate-800 text-gray-300
              hover:bg-slate-700 hover:border-blue-400
              ${isDragActive ? "border-blue-500 bg-slate-700" : "border-slate-600"}
            `}
          >
            <input {...getInputProps()} />
            <p className="text-lg text-center font-medium">
              {isDragActive
                ? "Drop the PDF here..."
                : "Drag & drop a PDF here, or click to select"}
            </p>
            <p className="text-sm text-gray-400">
              Only PDF files are accepted
            </p>

            {selectedFile && (
              <p className="text-sm text-green-400 mt-2">
                Selected file: {selectedFile.name}
              </p>
            )}
          </div>
        </section>
      )}
    </Dropzone>
  );
};

export default MyPdfDropzone;