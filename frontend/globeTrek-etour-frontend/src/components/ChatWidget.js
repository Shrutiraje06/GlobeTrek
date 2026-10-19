import React, { useState, useRef, useEffect } from "react";
import aiService from "../services/aiService";

const ChatWidget = () => {

  const [isOpen, setIsOpen] = useState(false);
  const [messages, setMessages] = useState([
    { role: "model", content: "Hi! Ask me anything about our tour packages 🌍" },
  ]);
  const [input, setInput] = useState("");
  const [loading, setLoading] = useState(false);

  const messagesEndRef = useRef(null);

  useEffect(() => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages, isOpen]);

  const handleSend = async (e) => {
    e.preventDefault();

    if (!input.trim()) return;

    const userMessage = { role: "user", content: input };
    const updatedMessages = [...messages, userMessage];

    setMessages(updatedMessages);
    setInput("");
    setLoading(true);

    try {
      const response = await aiService.chat(updatedMessages);
      setMessages([
        ...updatedMessages,
        { role: "model", content: response.data.reply },
      ]);
    } catch (error) {
      console.log(error);
      setMessages([
        ...updatedMessages,
        { role: "model", content: "Sorry, something went wrong. Please try again." },
      ]);
    }

    setLoading(false);
  };

  return (
    <>
      {/* Floating toggle button */}
      <button
        onClick={() => setIsOpen(!isOpen)}
        className="btn btn-primary rounded-circle shadow"
        style={{
          position: "fixed",
          bottom: "20px",
          right: "20px",
          width: "60px",
          height: "60px",
          fontSize: "24px",
          zIndex: 1050,
        }}
      >
        {isOpen ? "✕" : "🤖"}
      </button>

      {/* Chat window */}
      {isOpen && (
        <div
          className="card shadow"
          style={{
            position: "fixed",
            bottom: "90px",
            right: "20px",
            width: "320px",
            height: "420px",
            zIndex: 1050,
            display: "flex",
            flexDirection: "column",
          }}
        >
          <div className="card-header bg-primary text-white">
            IndiaTour Assistant
          </div>

          <div
            className="card-body"
            style={{ flex: 1, overflowY: "auto", fontSize: "0.9rem" }}
          >
            {messages.map((msg, i) => (
              <div
                key={i}
                className={`d-flex mb-2 ${msg.role === "user" ? "justify-content-end" : "justify-content-start"}`}
              >
                <div
                  className={`p-2 rounded ${msg.role === "user" ? "bg-primary text-white" : "bg-light"}`}
                  style={{ maxWidth: "80%", whiteSpace: "pre-line" }}
                >
                  {msg.content}
                </div>
              </div>
            ))}

            {loading && (
              <div className="d-flex justify-content-start mb-2">
                <div className="p-2 rounded bg-light text-muted">
                  Typing...
                </div>
              </div>
            )}

            <div ref={messagesEndRef} />
          </div>

          <form onSubmit={handleSend} className="card-footer d-flex p-2">
            <input
              type="text"
              className="form-control form-control-sm me-2"
              placeholder="Ask about a trip..."
              value={input}
              onChange={(e) => setInput(e.target.value)}
              disabled={loading}
            />
            <button className="btn btn-primary btn-sm" disabled={loading}>
              Send
            </button>
          </form>

        </div>
      )}
    </>
  );
};

export default ChatWidget;